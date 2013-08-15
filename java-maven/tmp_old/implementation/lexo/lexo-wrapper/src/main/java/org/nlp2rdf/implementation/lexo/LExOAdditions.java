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

package org.nlp2rdf.implementation.lexo;

import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.OWL;
import eu.lod2.nlp2rdf.schema.IThing;
import eu.lod2.nlp2rdf.schema.sso.IPhrase;
import eu.lod2.nlp2rdf.schema.sso.IWord;
import eu.lod2.nlp2rdf.schema.sso.Phrase;
import eu.lod2.nlp2rdf.schema.sso.Word;
import eu.lod2.nlp2rdf.schema.tools.Factory;
import org.nlp2rdf.core.URIGenerator;
import org.nlp2rdf.core.util.URIComparator;
import org.nlp2rdf.core.urischemes.URIGeneratorHelper;
import org.nlp2rdf.implementation.stanfordcore.StanfordCoreNLPWrapper;
import org.nlp2rdf.ontology.olia.OLiAManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;


/**
 * @author
 */

public class LExOAdditions {
    private static Logger log = LoggerFactory.getLogger(LExOAdditions.class);


    private String normUriString = "http://nlp2rdf.lod2.eu/schema/sso/normUri";
    private String lexoprefix = "http://nlp2rdf.lod2.eu/lexo#";

    private Set<String> filterLemma = new HashSet<String>(Arrays.asList(new String[]{"the", "a", ",", ".", "an"}));

    private Set<String> filterClasses = new HashSet<String>(Arrays
            .asList(new String[]{}));

    static {
        /***************************
         * Important requirement...
         */
        Factory.registerCustomClasses();
    }

    StanfordCoreNLPWrapper scw;

    public LExOAdditions(OLiAManager oLiAManager) {
        scw = new StanfordCoreNLPWrapper(oLiAManager);
    }

    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        URIGenerator ug = URIGeneratorHelper.determineGenerator("offset", 10);
        new LExOAdditions(new OLiAManager()).processText("http://test/test/", "A blue fish  is a sentence. ", ug, m);
        System.out.println(m);
    }

    public void processText(String prefix, String text, URIGenerator uriGenerator, OntModel diff) {
        scw.processText(prefix, text, uriGenerator, diff);

        ObjectProperty normUri = diff.createObjectProperty(normUriString);
        for (IWord w : Word.list(diff)) {
            standardize(w, normUri, prefix, diff);
        }
        for (IPhrase p : Phrase.list(diff)) {
            standardize(p, normUri, prefix, text, uriGenerator, diff);
        }
    }


    // creates the triple for the norm URI
    private void standardize(IWord word, ObjectProperty op, String prefix, OntModel model) {
        List<String> lemmas = word.listLemma();
        if (lemmas.size() == 0) {
            log.warn("no lemma found for " + word.getURI());
        } else {
            String lemmaString = lemmas.get(0);
            String uri = standardizeUri( lemmaString);
            word.addProperty(op, model.createIndividual(uri, OWL.Thing));
            log.info("assigning standardized URI(" + uri + ") to Word " + word.getURI());
        }
    }


    private void standardize(IPhrase phrase, ObjectProperty op, String prefix, String text, URIGenerator uriGenerator, OntModel model) {
        //System.out.println(phrase.listProperties().toList());
        TreeSet<IWord> wordsInOrder = new TreeSet<IWord>(new URIComparator(prefix, text, uriGenerator));

        //first check, if the phrase is a word only
        //no sorting is necessary
        if (model.getIndividual(phrase.getURI()).canAs(Word.class)) {
            wordsInOrder.add(Word.get(phrase.getURI(), model));

            //else iterate over all phrases until you reach the words
        } else {
            //queried is used to test whether the transitive closure has been reached, especially reflexive properties are problematic.
            Set<IThing> queried = new HashSet<IThing>();
            Set<IThing> current = new HashSet<IThing>();
            current.addAll(phrase.listChild());
            current.addAll(phrase.listSubString());
            queried.add(phrase);
            boolean repeat = true;
            while (repeat) {
                repeat = false;
                Set<IThing> next = new HashSet<IThing>();
                for (IThing i : current) {
                    if (queried.contains(i)) {
                        //do nothing
                    } else if (model.getIndividual(i.getURI()).canAs(Word.class)) {
                        Word w = Word.get(i.getURI(), model);
                        next.addAll(w.listSubString());
                        //repeat = (repeat || queried.addAll(next));
                        repeat = true;
                        wordsInOrder.add(w);
                    } else if (model.getIndividual(i.getURI()).canAs(Phrase.class)) {
                        Phrase p = Phrase.get(i.getURI(), model);
                        next.addAll(p.listSubString());
                        next.addAll(p.listChild());
                        repeat = true;
                        //repeat = (repeat || queried.addAll(next));
                    }
                    queried.add(i);
                    current = next;
                }
            }
        }

        if (log.isDebugEnabled()) {
            log.debug(phrase.getURI() + " in order " + wordsInOrder);
        }
        //filter stuff
        //filterPassiveAuxilliary(wordsInOrder);

        StringBuilder normalizedPart = new StringBuilder();
        boolean minimumOfOne = false;
        boolean first = true ;
        for (Iterator<IWord> it = wordsInOrder.iterator(); it.hasNext(); ) {
            IWord w = it.next();
            List<String> lemmas = w.listLemma();
            if (!lemmas.isEmpty()) {
                String lemmaString = lemmas.get(0);

                if (filterLemma.contains(lemmaString)) {
                    log.debug("filtered out: " + phrase.getAnchorOf() + " : " + lemmaString);
                    continue;
                }

                if(first){
                    first = false;
                } else {
                    normalizedPart.append("_");
                }
                normalizedPart.append(lemmaString);
                minimumOfOne = true;
            }
        }
        if (minimumOfOne) {
            String uri = standardizeUri( normalizedPart.toString());
            phrase.addProperty(op, model.createIndividual(uri, OWL.Thing));
            log.info("assigning standardized URI(" + uri + ") to Phrase " + phrase.getURI());
        } else {
            log.error("no lemma found for any word of phrase: " + phrase);
        }
    }

    private String standardizeUri(String lemma) {

        StringBuilder sb = new StringBuilder();
        sb.append(lexoprefix);
        //sb.append("lexo");
        //if (prependUnderscore) {
        //    sb.append("_");
        //}
        // allow the lemma to appear in the URL
        try {
            sb.append(URLEncoder.encode(lemma.trim().toLowerCase(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("", e);
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

}


/*protected  void process() {
   OntModel documentModel = d.getModel();

   //add two new subclasses of Phrase class
   OntClass phrasalVerb = documentModel.createClass(tokenWithPrepositionClass);
   OntClass compoundNoun = documentModel.createClass(compoundNounClass);

   phrasalVerb.addSuperClass(documentModel.getOntClass(BackboneVocabulary.phraseClass));
   compoundNoun.addSuperClass(documentModel.getOntClass(BackboneVocabulary.phraseClass));


   Set<Individual> tokens = new HashSet<Individual>();
   tokens.addAll(documentModel.listIndividuals(documentModel.getOntClass(BackboneVocabulary.wordClass)).toSet());
   //allVerbs.addAll(documentModel.listIndividuals(documentModel.getOntClass(OliaVocabulary.olia_adject)).toSet());

   ObjectProperty PrepositionalModifier = documentModel
           .getObjectProperty("http://nachhalt.sfb632.uni-potsdam.de/owl/stanford.owl#PrepositionalModifier");
   ObjectProperty NominalModifier = documentModel
           .getObjectProperty("http://nachhalt.sfb632.uni-potsdam.de/owl/stanford.owl#NounCompoundModifier");

   if (PrepositionalModifier == null) {
       logger.warn("did not find "
               + "http://nachhalt.sfb632.uni-potsdam.de/owl/stanford.owl#PrepositionalModifier");
       return null;
   }

   for (Individual token : tokens) {
       mergeWithDependency(PrepositionalModifier, token, phrasalVerb);
       mergeWithDependency(NominalModifier, token, compoundNoun);

   }

   return null;
}

private void mergeWithDependency(ObjectProperty dependencyRelation, Individual token, OntClass clazz) {
     if (token == null) {
         logger.error("token was null");
         return;
     }

     RDFNode n = token.getPropertyValue(dependencyRelation);
     if (n == null) {
         return;
     } else {
         Individual prep = n.as(Individual.class);
         Individual mergedToken = TokenHelper.mergeTokens(token, prep);
         if (mergedToken == null) {
             logger.error("mergedToken was null");
             return;
         }

         mergedToken.addOntClass(clazz);

     }


 }
*/