/***************************************************************************/
/*  Copyright (C) 2010-2011, Sebastian Hellmann                            */
/*  Note: If you need parts of NLP2RDF in another licence due to licence   */
/*  incompatibility, please mail hellmann@informatik.uni-leipzig.de        */
/*                                                                         */
/*  This file is part of NLP2RDF.                                          */
/*                                                                         */
/*  NLP2RDF is free software; you can redistribute it and/or modify        */
/*  it under the terms of the GNU General Public License as published by   */
/*  the Free Software Foundation; either version 3 of the License, or      */
/*  (at your option) any later version.                                    */
/*                                                                         */
/*  NLP2RDF is distributed in the hope that it will be useful,             */
/*  but WITHOUT ANY WARRANTY; without even the implied warranty of         */
/*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the           */
/*  GNU General Public License for more details.                           */
/*                                                                         */
/*  You should have received a copy of the GNU General Public License      */
/*  along with this program. If not, see <http://www.gnu.org/licenses/>.   */
/***************************************************************************/

package org.nlp2rdf.implementation.stanfordcore;

import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.semgraph.SemanticGraph;
import edu.stanford.nlp.trees.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.util.CoreMap;
import eu.lod2.nlp2rdf.schema.Thing;
import eu.lod2.nlp2rdf.schema.sso.Phrase;
import eu.lod2.nlp2rdf.schema.sso.Word;
import eu.lod2.nlp2rdf.schema.tools.Factory;
import org.nlp2rdf.core.Span;
import org.nlp2rdf.core.Text2RDF;
import org.nlp2rdf.core.URIGenerator;
import org.nlp2rdf.ontology.olia.OLiAManager;
import org.nlp2rdf.ontology.olia.OLiAOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The basic code was taken from the ClearTK Project
 * http://code.google.com/p/cleartk
 * who have written a UIMA wrapper.
 * The original file by Steven Bethard can be found here:
 * http://code.google.com/p/cleartk/source/browse/trunk/cleartk-stanford-corenlp/src/main/java/org/cleartk/stanford/StanfordCoreNLPAnnotator.java
 * Licence http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * @author Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 */

public class StanfordCoreNLPWrapper {
    private static Logger log = LoggerFactory.getLogger(StanfordCoreNLPWrapper.class);


    private final OLiAOntology penn;
    private final OLiAOntology penn_syntax;
    private final OLiAOntology dependency;

    static {
        /***************************
         * Important requirement...
         */
        Factory.registerCustomClasses();
    }


    public StanfordCoreNLPWrapper(OLiAManager oLiAManager) {
        penn = oLiAManager.getOLiAOntology("http://purl.org/olia/penn-link.rdf");
        penn_syntax = oLiAManager.getOLiAOntology("http://purl.org/olia/penn-syntax-link.rdf");
        //we only use the annotation model here
        dependency = oLiAManager.getOLiAOntology("http://purl.org/olia/stanford.owl");
    }

    public void processText(String prefix, String text, URIGenerator urigenerator, OntModel model) {

        /**
         * Prepare Stanford
         **/
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        //props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        //props.put("annotators", "tokenize, ssplit, pos, lemma, parse, ner"); // ner,  dcoref");
        //props.put("annotators", "tokenize, ssplit, pos, lemma, parse"); // ner,  dcoref");
        props.put("annotators", "tokenize, ssplit, pos, lemma"); // ner,  dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);

        // these are all the sentences in this document
        // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);

        //get all the sentences and words and read it in an intermediate structure
        TreeMap<Span, List<Span>> tokenizedText = new TreeMap<Span, List<Span>>();
        for (CoreMap sentence : sentences) {
            Span sentenceSpan = new Span(sentence.get(CharacterOffsetBeginAnnotation.class), sentence.get(CharacterOffsetEndAnnotation.class));
            List<Span> wordSpans = new ArrayList<Span>();
            for (CoreLabel coreLabel : sentence.get(TokensAnnotation.class)) {
                wordSpans.add(new Span(coreLabel.get(CharacterOffsetBeginAnnotation.class), coreLabel.get(CharacterOffsetEndAnnotation.class)));
            }
            tokenizedText.put(sentenceSpan, wordSpans);
        }

        /**
         * Basic Model Setup
         **/
        //get parameters for the URIGenerator
        Text2RDF text2RDF = new Text2RDF();
        Individual context = text2RDF.creatC(prefix, text, urigenerator, model);
        text2RDF.generateNIFModel(prefix, text, tokenizedText, urigenerator, context, model);
        //add additional data
        //removed for 2.0
        //text2RDF.addNextAndPreviousProperties(prefix, text, urigenerator, model);

        // traversing the words in the current sentence
        // a CoreLabel is a CoreMap with additional token-specific methods
        for (CoreMap sentence : sentences) {

            /********************************
             * Word, Lemma and Pos tags
             ******/
            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
                Span wordSpan = new Span(token.get(CharacterOffsetBeginAnnotation.class), token.get(CharacterOffsetEndAnnotation.class));
                //the word should exist already
                Word word = Word.get(urigenerator.makeUri(prefix, text, wordSpan), model);
                if (word == null) {
                    log.error("SKIPPING: word was not found in the model: " + urigenerator.makeUri(prefix, text, wordSpan));
                    continue;
                }
                word.addLemma(token.get(LemmaAnnotation.class));
                // this is the POS tag of the token
                String posTag = token.get(PartOfSpeechAnnotation.class);
                word.addPosTag(posTag);
                String oliaIndividual = null;
                if ((oliaIndividual = penn.getIndividualURIForTag(posTag)) != null) {
                    word.addOliaLink(Thing.create(oliaIndividual, model));
                }

                //adding pos classes from olia and olia-top
                /* THIS WAS MOVED TO AN EXTRA MODULE
                Set<String> classes = penn.getClassURIsForTag(posTag);
                for (String classUri : classes) {
                    log.info("found: " + classUri + " for: " + posTag);
                    OntModel hierarchy = penn.getHierarchy(classUri);
                    for (ExtendedIterator<OntClass> it = hierarchy.listClasses(); it.hasNext(); ) {
                        OntClass oc = it.next();
                    //use all classes
                    //if (oc.getURI().startsWith("http://purl.org/olia/olia-top.owl") || oc.getURI().startsWith("http://purl.org/olia/olia.owl")) {
                        w.addOntClass(diff.createResource(oc.getURI()));
                    //}
                    }
                    //Copy the hierarchy
                    diff.add(hierarchy);
                }
                */

            }//end token

            //here OLiA classes are added
            //removed for 2.0
            //text2RDF.addCopyOfOLiAClassesAndHierarchy(penn, model);


            /*************************
             * Named Entities
             */
            //Object o = sentence.get(NamedEntityTagAnnotation.class)    ;
            //   System.out.println(o);

            /********************************
             * Dependencies for each sentence
             ******/
            if (true) return ;
            SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);

            if (dependencies != null) {
                // create relation annotations for each Stanford dependency
                for (SemanticGraphEdge stanfordEdge : dependencies.edgeList()) {

                    Span govSpan = new Span(stanfordEdge.getGovernor().get(CharacterOffsetBeginAnnotation.class), stanfordEdge.getGovernor().get(CharacterOffsetEndAnnotation.class));
                    Span depSpan = new Span(stanfordEdge.getDependent().get(CharacterOffsetBeginAnnotation.class), stanfordEdge.getDependent().get(CharacterOffsetEndAnnotation.class));
                    String relationType = stanfordEdge.getRelation().toString();
                    Word gov = Word.get(urigenerator.makeUri(prefix, text, govSpan), model);
                    Word dep = Word.get(urigenerator.makeUri(prefix, text, depSpan), model);

                    if (gov == null || dep == null) {
                        log.error("SKIPPING Either gov or dep was null for the dependencies");
                        log.error("gov: " + gov);
                        log.error("dep: " + dep);
                        continue;
                    }

                    Individual relation =  dependency.getOLiAIndividualForTag(relationType);

                    //in an ideal world, all used tags should also be in OLiA, this tends to be null sometimes
                    if (relation == null) {
                        log.error("reltype was null for: " + relationType);
                        continue;
                    }

                    ObjectProperty dependencyRelation = model.createObjectProperty(relation.getURI());
                    //add the property from governer to dependent
                    gov.addProperty(dependencyRelation, dep);


                    Set<String> classUris = dependency.getClassURIsForTag(relationType);
                    for (String cl : classUris) {
                        if (!cl.startsWith("http://purl.org/olia/stanford.owl")) {
                            continue;
                        }
                        //add the property from governer to dependent
                        ObjectProperty nn = model.createObjectProperty(cl);
                        gov.addProperty(nn, dep);
                        dependencyRelation.addSuperProperty(nn);

                        //copy and transform the hierarchy
                        //removed for 2.0
                        //OLiAOntology.classHierarchy2PropertyHierarchy(dependency.getHierarchy(cl), model, "http://purl.org/olia/stanford.owl");
                    }
                }

            }

            /**************
             * Syntax Tree
             * */

            Tree tree = sentence.get(TreeAnnotation.class);
            if (tree != null) {
                //removed for 2.0
                //processTree(tree, urigenerator, prefix, text, model);
            }

        }

        //log.info("Added lemma, pos, olia having " + (diff.size() - size) + " more triples.");
        //size = diff.size();
        //log.info("Added dependencies: " + (diff.size() - size) + " more triples.");
        //size = diff.size();
    }


    public void processTree(Tree currentNode, URIGenerator uriGenerator, String prefix, String text, OntModel model) {
        // String tag = currentNode.label().value();
        //log.info("Current Node :" + currentNode);
        //log.info("Label: " + currentNode.label() + "");
        //log.info("Label Value: " + currentNode.label().value() + "");
        //log.info("Preterminal: " + currentNode.isPreTerminal() + "");
        //log.info("Index: " + ((CoreLabel) currentNode.label()).get(CharacterOffsetBeginAnnotation.class) + "");


        if (currentNode.isLeaf()) {
            //the node is a leaf and belongs in the realm of pos tagging
        } else {
            Phrase p = new Text2RDF().createStringAnnotationForClass(Phrase.class, prefix, text, getSpan(currentNode), uriGenerator, model);
            List<Tree> children = currentNode.getChildrenAsList();
            for (Tree child : children) {


                /* if (false && child.isPreTerminal()) {
                //skip preterminals
                log.debug("skipping preterminal: "+currentNode);
                log.debug("label: "+currentNode.label());
                child = child.getChild(0);
                Word childTerminal = new Text2RDF().createStringAnnotationForClass(Word.class, prefix, text, getSpan(child), uriGenerator, model);
                p.addChild(childTerminal);
                 */
                Phrase childPhrase = new Text2RDF().createStringAnnotationForClass(Phrase.class, prefix, text, getSpan(child), uriGenerator, model);
                p.addChild(childPhrase);
                processTree(child, uriGenerator, prefix, text, model);

                log.info("Current Node :" + currentNode);
                log.info("Label: " + currentNode.label() + "");
                log.info("Label Value: " + currentNode.label().value() + "");
                log.info("Preterminal: " + currentNode.isPreTerminal() + "");
                //log.info("Index: " + ((CoreLabel) currentNode.label()).get(CharacterOffsetBeginAnnotation.class) + "");

                //adding syntax classes from olia and olia-top
                String tag = ((CoreLabel) currentNode.label()).get(CategoryAnnotation.class);
                Set<String> classes = penn_syntax.getClassURIsForTag(tag);
                for (String classUri : classes) {
                    log.info("found: " + classUri + " for: " + tag);
                    OntModel hierarchy = penn_syntax.getHierarchy(classUri);
                    for (ExtendedIterator<OntClass> it = hierarchy.listClasses(); it.hasNext(); ) {
                        OntClass oc = it.next();
                        p.addOntClass(model.createResource(oc.getURI()));
                    }
                    //Copy the hierarchy
                    model.add(hierarchy);
                }
            }
        }
    }


    private Span getSpan(Tree tree) {
        List<Tree> leaves = tree.getLeaves();
        int beginIndex = (int) ((CoreLabel) leaves.get(0).label()).get(CharacterOffsetBeginAnnotation.class);
        int endIndex = (int) ((CoreLabel) leaves.get(leaves.size() - 1).label()).get(CharacterOffsetEndAnnotation.class);
        Span currentSpan = new Span(beginIndex, endIndex);
        return currentSpan;
    }

}
/* @Override
   public void process(String text) {
       Annotation document = this.processor.process(text);

       String lastNETag = "O";
       int lastNEBegin = -1;
       int lastNEEnd = -1;
       for (CoreMap tokenAnn : document.get(TokensAnnotation.class)) {

           // create the token annotation
           int begin = tokenAnn.get(CharacterOffsetBeginAnnotation.class);
           int end = tokenAnn.get(CharacterOffsetEndAnnotation.class);
           String pos = tokenAnn.get(PartOfSpeechAnnotation.class);
           String lemma = tokenAnn.get(LemmaAnnotation.class);

            Token token = new Token(jCas, begin, end);
             token.setPos(pos);
             token.setLemma(lemma);
             token.addToIndexes();
           // hackery to convert token-level named entity tag into phrase-level tag
           String neTag = tokenAnn.get(NamedEntityTagAnnotation.class);
           if (neTag.equals("O") && !lastNETag.equals("O")) {
              NamedEntityMention ne = new NamedEntityMention(jCas, lastNEBegin, lastNEEnd);
              ne.setMentionType(lastNETag);
              ne.addToIndexes();

           } else {
               if (lastNETag.equals("O")) {
                   lastNEBegin = begin;
               } else if (lastNETag.equals(neTag)) {
                   // do nothing - begin was already set
               } else {
                   NamedEntityMention ne = new NamedEntityMention(jCas, lastNEBegin, lastNEEnd);
                   ne.setMentionType(lastNETag);
                   ne.addToIndexes();
                   lastNEBegin = begin;
               }
               lastNEEnd = end;
           }
           lastNETag = neTag;
       }
       if (!lastNETag.equals("O")) {
           NamedEntityMention ne = new NamedEntityMention(jCas, lastNEBegin, lastNEEnd);
           ne.setMentionType(lastNETag);
           ne.addToIndexes();
       }

       // add sentences and trees
       for (CoreMap sentenceAnn : document.get(SentencesAnnotation.class)) {

           // add the sentence annotation
           int sentBegin = sentenceAnn.get(CharacterOffsetBeginAnnotation.class);
           int sentEnd = sentenceAnn.get(CharacterOffsetEndAnnotation.class);
           Sentence sentence = new Sentence(jCas, sentBegin, sentEnd);
           sentence.addToIndexes();

           // add the syntactic tree annotation
           List<CoreLabel> tokenAnns = sentenceAnn.get(TokensAnnotation.class);
           Tree tree = sentenceAnn.get(TreeAnnotation.class);
           if (tree.children().length != 1) {
               throw new RuntimeException("Expected single root node, found " + tree);
           }
           tree = tree.firstChild();
           tree.indexSpans(0);
           TopTreebankNode root = new TopTreebankNode(jCas);
           root.setTreebankParse(tree.toString());
           // TODO: root.setTerminals(v)
           this.addTreebankNodeToIndexes(root, jCas, tree, tokenAnns);

           // get the dependencies
           SemanticGraph dependencies = sentenceAnn.get(CollapsedCCProcessedDependenciesAnnotation.class);

           // convert Stanford nodes to UIMA annotations
           List<Token> tokens = JCasUtil.selectCovered(jCas, Token.class, sentence);
           Map<IndexedWord, DependencyNode> stanfordToUima = new HashMap<IndexedWord, DependencyNode>();
           for (IndexedWord stanfordNode : dependencies.vertexSet()) {
               int indexBegin = stanfordNode.get(BeginIndexAnnotation.class);
               int indexEnd = stanfordNode.get(EndIndexAnnotation.class);
               int tokenBegin = tokens.get(indexBegin).getBegin();
               int tokenEnd = tokens.get(indexEnd - 1).getEnd();
               DependencyNode node;
               if (dependencies.getRoots().contains(stanfordNode)) {
                   node = new TopDependencyNode(jCas, tokenBegin, tokenEnd);
               } else {
                   node = new DependencyNode(jCas, tokenBegin, tokenEnd);
               }
               stanfordToUima.put(stanfordNode, node);
           }

           // create relation annotations for each Stanford dependency
           ArrayListMultimap<DependencyNode, DependencyRelation> headRelations = ArrayListMultimap.create();
           ArrayListMultimap<DependencyNode, DependencyRelation> childRelations = ArrayListMultimap.create();
           for (SemanticGraphEdge stanfordEdge : dependencies.edgeList()) {
               DependencyRelation relation = new DependencyRelation(jCas);
               DependencyNode head = stanfordToUima.get(stanfordEdge.getGovernor());
               DependencyNode child = stanfordToUima.get(stanfordEdge.getDependent());
               String relationType = stanfordEdge.getRelation().toString();
               if (head == null || child == null || relationType == null) {
                   throw new RuntimeException(String.format("null elements not allowed in relation:\nrelation=%s\nchild=%s\nhead=%s\n", relation, child, head));
               }
               relation.setHead(head);
               relation.setChild(child);
               relation.setRelation(relationType);
               relation.addToIndexes();
               headRelations.put(child, relation);
               childRelations.put(head, relation);
           }

           // set the relations for each node annotation
           for (DependencyNode node : stanfordToUima.values()) {
               node.setHeadRelations(UIMAUtil.toFSArray(jCas, headRelations.get(node)));
               node.setChildRelations(UIMAUtil.toFSArray(jCas, childRelations.get(node)));
               node.addToIndexes();
           }
       }

       // map from tokens to their smallest containing named entity mentions
       Map<Span, NamedEntityMention> tokenMentionMap = new HashMap<Span, NamedEntityMention>();
       for (NamedEntityMention mention : JCasUtil.select(jCas, NamedEntityMention.class)) {
           for (Token token : JCasUtil.selectCovered(jCas, Token.class, mention)) {
               Span span = new Span(token.getBegin(), token.getEnd());
               NamedEntityMention oldMention = tokenMentionMap.get(span);
               if (oldMention == null || AnnotationUtil.size(mention) < AnnotationUtil.size(oldMention)) {
                   tokenMentionMap.put(span, mention);
               }
           }
       }

       // add mentions for all entities identified by the coreference system
       CorefGraph corefGraph = new CorefGraph(document.get(CorefGraphAnnotation.class));
       Map<CoreMap, NamedEntityMention> stanfordToUimaNE = new HashMap<CoreMap, NamedEntityMention>();
       for (CoreMap tokenMap : corefGraph.getMentions(document)) {
           NamedEntityMention mention = null;

           // figure out the character span of the token
           int begin = tokenMap.get(CharacterOffsetBeginAnnotation.class);
           int end = tokenMap.get(CharacterOffsetEndAnnotation.class);

           // if a named entity already contains the token, use that
           mention = tokenMentionMap.get(new Span(begin, end));

           // otherwise, create a new named entity mention
           if (mention == null) {
               Token token = new Token(jCas, begin, end);
               for (TreebankNode node : JCasUtil.selectCovered(jCas, TreebankNode.class, token)) {
                   // if the token is a PRP, use that
                   if (node.getNodeType().startsWith("PRP")) {
                       begin = node.getBegin();
                       end = node.getEnd();
                       break;
                   }
                   // if the token's parent is an NP, use that
                   TreebankNode parent = node.getParent();
                   if (node.getLeaf() && parent != null && parent.getNodeType().equals("NP")) {
                       begin = parent.getBegin();
                       end = parent.getEnd();
                       break;
                   }
               }
               // create the named entity mention (defaulting to the same span as the token)
               mention = new NamedEntityMention(jCas, begin, end);
               mention.addToIndexes();
           }

           // update the token -> mention mapping
           stanfordToUimaNE.put(tokenMap, mention);
       }

       // link mentions into their entities
       List<NamedEntity> entities = new ArrayList<NamedEntity>();
       for (Set<CoreMap> tokenMaps : corefGraph.getEntities(document)) {

           // sort mentions by document order
           List<CoreMap> tokenMapsList = new ArrayList<CoreMap>(tokenMaps);
           Collections.sort(tokenMapsList, new Comparator<CoreMap>() {
               @Override
               public int compare(CoreMap o1, CoreMap o2) {
                   int begin1 = o1.get(CharacterOffsetBeginAnnotation.class);
                   int begin2 = o2.get(CharacterOffsetBeginAnnotation.class);
                   return begin1 - begin2;
               }
           });

           // create mentions and add them to entity
           NamedEntity entity = new NamedEntity(jCas);
           entity.setMentions(new FSArray(jCas, tokenMapsList.size()));
           int index = 0;
           for (CoreMap tokenMap : tokenMapsList) {
               NamedEntityMention mention = stanfordToUimaNE.get(tokenMap);
               mention.setMentionedEntity(entity);
               entity.setMentions(index, mention);
               index += 1;
           }
           entities.add(entity);
       }

       // add singleton entities for any named entities not picked up by coreference system
       for (NamedEntityMention mention : JCasUtil.select(jCas, NamedEntityMention.class)) {
           if (mention.getMentionedEntity() == null) {
               NamedEntity entity = new NamedEntity(jCas);
               entity.setMentions(new FSArray(jCas, 1));
               entity.setMentions(0, mention);
               mention.setMentionedEntity(entity);
               entity.getMentions();
               entities.add(entity);
           }
       }

       // sort entities by document order
       Collections.sort(entities, new Comparator<NamedEntity>() {
           @Override
           public int compare(NamedEntity o1, NamedEntity o2) {
               return getFirstBegin(o1) - getFirstBegin(o2);
           }

           private int getFirstBegin(NamedEntity entity) {
               int min = Integer.MAX_VALUE;
               for (NamedEntityMention mention : JCasUtil.select(entity.getMentions(), NamedEntityMention.class)) {
                   if (mention.getBegin() < min) {
                       min = mention.getBegin();
                   }
               }
               return min;
           }
       });

       // add entities to document
       for (NamedEntity entity : entities) {
           entity.addToIndexes();
       }

   }

   private FSArray addTreebankNodeChildrenToIndexes(TreebankNode parent, JCas jCas, List<CoreLabel> tokenAnns, Tree tree) {
       Tree[] childTrees = tree.children();

       // collect all children (except leaves, which are just the words - POS tags are pre-terminals in
       // a Stanford tree)
       List<TreebankNode> childNodes = new ArrayList<TreebankNode>();
       for (Tree child : childTrees) {
           if (!child.isLeaf()) {

               // set node attributes and add children (mutual recursion)
               TreebankNode node = new TreebankNode(jCas);
               node.setParent(parent);
               this.addTreebankNodeToIndexes(node, jCas, child, tokenAnns);
               childNodes.add(node);
           }
       }

       // convert the child list into an FSArray
       FSArray childNodeArray = new FSArray(jCas, childNodes.size());
       for (int i = 0; i < childNodes.size(); ++i) {
           childNodeArray.set(i, childNodes.get(i));
       }
       return childNodeArray;
   }

   private void addTreebankNodeToIndexes(TreebankNode node, JCas jCas, Tree tree, List<CoreLabel> tokenAnns) {
       // figure out begin and end character offsets
       CoreMap label = (CoreMap) tree.label();
       CoreMap beginToken = tokenAnns.get(label.get(BeginIndexAnnotation.class));
       CoreMap endToken = tokenAnns.get(label.get(EndIndexAnnotation.class) - 1);
       int nodeBegin = beginToken.get(CharacterOffsetBeginAnnotation.class);
       int nodeEnd = endToken.get(CharacterOffsetEndAnnotation.class);

       // set span, node type, children (mutual recursion), and add it to the JCas
       node.setBegin(nodeBegin);
       node.setEnd(nodeEnd);
       node.setNodeType(tree.value());
       node.setChildren(this.addTreebankNodeChildrenToIndexes(node, jCas, tokenAnns, tree));
       node.setLeaf(node.getChildren().size() == 0);
       node.addToIndexes();
   }

   private static class Span {
       public int begin;

       public int end;

       public Span(int begin, int end) {
           this.begin = begin;
           this.end = end;
       }

       public boolean equals(Object object) {
           if (object instanceof Span) {
               Span that = (Span) object;
               return this.begin == that.begin && this.end == that.end;
           } else {
               return false;
           }
       }

       public int hashCode() {
           return Arrays.hashCode(new int[]{this.begin, this.end});
       }
   }
}
*/


