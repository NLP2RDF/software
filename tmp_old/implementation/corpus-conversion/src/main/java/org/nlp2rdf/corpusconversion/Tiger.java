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

package org.nlp2rdf.corpusconversion;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import org.apache.commons.collections15.multimap.MultiHashMap;
import org.apache.log4j.Logger;
import org.nlp2rdf.core.BackboneVocabulary;
import org.nlp2rdf.core.ModelManager;
import org.nlp2rdf.core.TokenManager;
import org.nlp2rdf.ontologyconnector.OliaPOSOntologyConnector;
import org.nlp2rdf.ontologyconnector.OliaSyntaxOntologyConnector;
import org.nlp2rdf.plugin.Plugin;
import org.nlp2rdf.plugin.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Sebastian Hellmann <hellmann@informatik.uni-leipzig.de>
 */
public class Tiger implements Plugin {
    private static final Logger logger = Logger.getLogger(Tiger.class);

    List<OneLine> oneEntry = new ArrayList<OneLine>();
    OliaPOSOntologyConnector oliaPOSOntologyConnector = new OliaPOSOntologyConnector();
    OliaSyntaxOntologyConnector oliaSyntaxOntologyConnector = new OliaSyntaxOntologyConnector();

    public Tiger() {
        oliaPOSOntologyConnector.setOntologyUri("http://nachhalt.sfb632.uni-potsdam.de/owl/tiger-link.rdf");
        oliaSyntaxOntologyConnector.setOntologyUri("http://nachhalt.sfb632.uni-potsdam.de/owl/tiger-link.rdf");
    }

    public List<OntModel> process(List<OntModel> models) {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Result process(OntModel model) {
        return null;
    }

    public Result process(OntModel model, List<OneLine> oneEntry) {
        this.oneEntry = oneEntry;
        Result r = new Result(model);
        Set<Individual> sentences = ModelManager.getSentences(model);
        for (Individual sentence : sentences) {
            processOneSentence(sentence, r);
        }

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void processOneSentence(Individual sentence, Result r) {
        addLemmas(sentence, r);
        addPosTags(sentence, r);
        addSyntax(sentence, r);

    }

    private void addSyntax(Individual sentence, Result r) {
        OliaSyntaxOntologyConnector.SyntaxTree root = oliaSyntaxOntologyConnector.createSyntaxTreeRoot(sentence);
        MultiHashMap mhm = new MultiHashMap<String, OneLine>();
        for (OneLine ol : oneEntry) {
            mhm.put(ol.parent, ol);
        }

        OneLine currentLine = new OneLine();
        currentLine.word = "#0";
        currentLine.edge = "--";
        processTree(currentLine, root, mhm, sentence);

        oliaSyntaxOntologyConnector.connect(r.model);
        //System.out.println(mhm);
        //System.exit(0);

    }

    public static void processTree(OneLine currentLine, OliaSyntaxOntologyConnector.SyntaxTree synTree, MultiHashMap mhm, Individual sentence) {
        //edge label


        logger.debug("Syntax current line: " + currentLine);
        if (currentLine.isWord()) {

            logger.debug("leaf position: " + currentLine.position);
            Individual token = getWordAtPosition(sentence, currentLine.position);
            assert (token != null);
            OliaSyntaxOntologyConnector.SyntaxTree leaf = synTree.addLeaf(token, currentLine.word, currentLine.position);
            if (!currentLine.edge.equals("--")) {
                leaf.setEdgeLabel(currentLine.edge);
            }
        } else {
            logger.debug("adding new syntax node");
            List<OneLine> children = (List<OneLine>) mhm.get(currentLine.word.replace("#", ""));
            /* System.out.println(children);
            for (Object s : mhm.keySet()) {
                String words = "";
                for (OneLine ol : (List<OneLine>) mhm.get(s)) {
                    words += ol.word+", ";
                }
                logger.debug("key:" + s + "  " + words);
            }*/

            for (int x = 0; x < children.size(); x++) {
                OneLine child = children.get(x);
                logger.debug("child line " + child);
                if (child.isWord()) {
                    processTree(child, synTree, mhm, sentence);
                } else {
                    OliaSyntaxOntologyConnector.SyntaxTree astTreeChild = synTree.addNode(child.tag);
                    if (!child.edge.equals("--")) {
                        astTreeChild.setEdgeLabel(child.edge);
                    }
                    processTree(child, astTreeChild, mhm, sentence);
                }

            }

        }
    }

    private void addPosTags(Individual sentence, Result r) {
        Individual currentWord = getFirstWord(sentence);
        for (OneLine oneline : oneEntry) {
            if (!oneline.isWord()) {
                continue;
            }
            oliaPOSOntologyConnector.addPosTuple(currentWord.getURI(), oneline.tag);
            if (TokenManager.hasNextToken(currentWord)) {
                currentWord = TokenManager.nextToken(currentWord);
            }
        }
        oliaPOSOntologyConnector.connect(r.model);

    }

    private void addLemmas(Individual sentence, Result r) {
        Individual currentWord = getFirstWord(sentence);
        DatatypeProperty hasLemma = r.model.getDatatypeProperty(BackboneVocabulary.hasLemmaProperty);

        for (OneLine oneline : oneEntry) {
            if (!oneline.hasLemma()) {
                continue;
            }
            currentWord.addProperty(hasLemma, oneline.lemma);
            if (TokenManager.hasNextToken(currentWord)) {
                currentWord = TokenManager.nextToken(currentWord);
            }

        }

    }

    public static Individual getWordAtPosition(Individual sentence, int position) {
        Individual currentWord = getFirstWord(sentence);
        do {
            RDFNode n = currentWord.getPropertyValue(currentWord.getModel().getProperty(BackboneVocabulary.positionProperty));
            if (n.asLiteral().getInt() == position) {
                return currentWord;
            }
            currentWord = TokenManager.nextToken(currentWord);

        } while (true);

    }

    public static Individual getFirstWord(Individual sentence) {

        OntModel m = sentence.getOntModel();
        ObjectProperty firstWordProperty = m.getObjectProperty(BackboneVocabulary.firstWordProperty);
        Resource r = sentence.getPropertyResourceValue(firstWordProperty);

        // Individual firstWord = m.getIndividual(r.getURI());
        Individual firstWord = r.as(Individual.class);
        assert (firstWord != null);
        return firstWord;
    }
}
