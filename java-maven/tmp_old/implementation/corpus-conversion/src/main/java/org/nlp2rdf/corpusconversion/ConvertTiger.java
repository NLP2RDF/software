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


import com.hp.hpl.jena.ontology.OntModel;
import org.aksw.commons.jena_owlapi.Conversion;
import org.aksw.commons.owlapi.SaveOntology;
import org.aksw.commons.util.Files;
import org.apache.log4j.Logger;
import org.nlp2rdf.core.ModelManager;
import org.nlp2rdf.preprocessing.Sentence2OWL;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Creates in outputfolder:
 * - an xml file containing the nlp2rdf.lod2.eu.schema
 * - an xml file for each sentence containing the data for one sentence
 * - an nt file for each sentence containing the data for one sentence (with inferred triples)
 *
 * @author Sebastian Hellmann <hellmann@informatik.uni-leipzig.de>
 */
public class ConvertTiger {
    private static final Logger logger = Logger.getLogger(ConvertTiger.class);


    public static void main(String[] args) {
        String inputFilename = "";
        String outputFolder = "";
        try {
            inputFilename = args[0];
            outputFolder = args[1];
        } catch (Exception e) {
            inputFilename = "tiger.export";
            outputFolder = "tigeroutput";
            logger.info("Failed to find correct parameters:\n" + "inputfile outputfolder \n" + "using defaults:\n" + inputFilename + " " + outputFolder);
        }

        BufferedReader reader = getReader(new File(inputFilename));

        Files.mkdir(new File(outputFolder));
        String tigerXMLFolder = outputFolder + "/tigerxml";
        Files.mkdir(new File(tigerXMLFolder));
        String tigerNTFolder = outputFolder + "/turtle";
        Files.mkdir(new File(tigerNTFolder));


        List<OneLine> oneEntry;
        int sentencenumber = 1;
        OntModel schemaModel = ModelManager.createDefaultModel();
        ModelManager.loadSSO(schemaModel);

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology wholeSchema = null;
        try {
            wholeSchema = manager.createOntology();
        } catch (OWLOntologyCreationException e) {
            logger.error("", e);
        }

        Tiger tigerconverter = new Tiger();
        while ((oneEntry = getNextSentence(reader)) != null) {
            List<String> tokens = new ArrayList<String>();
            StringBuffer buf = new StringBuffer();
            //get the Strings of the words
            //add a position counter to the line
            for (int i = 0; i < oneEntry.size(); i++) {

                OneLine oneLine = oneEntry.get(i);
                if (oneLine.word.startsWith("#")) {
                    continue;
                }
                buf.append(oneLine.word + " ");
                tokens.add(oneLine.word);
                oneLine.position = i;
            }

            String sentenceURI = "http://tiger.nlp2rdf.org/s" + sentencenumber;
            String sentenceString = buf.toString().trim();


            logger.info("next sentence " + sentencenumber + " " + sentenceString);
            OntModel sentenceModel = ModelManager.createDefaultModel();
            Sentence2OWL.process(sentenceModel, sentenceURI, sentenceString, tokens.toArray(new String[tokens.size()]));
            sentenceModel.add(schemaModel);

            System.out.println(sentenceModel);
            tigerconverter.process(sentenceModel, oneEntry);


            //save the xml files
            String outputSentenceDataXML = "";
            try {
                outputSentenceDataXML = tigerXMLFolder + "/" + URLEncoder.encode(sentenceURI, "UTF-8") + ".owl";
            } catch (UnsupportedEncodingException e) {
                logger.error("", e);
            }
            org.aksw.commons.jena.SaveOntology.saveAsRDFXML(sentenceModel, outputSentenceDataXML);


            OWLOntology tmp = Conversion.JenaModel2OWLAPIOntology(sentenceModel);


            for (OWLAxiom ax : tmp.getAxioms()) {
                if (ax instanceof OWLIndividualAxiom) {
                    continue;
                }
                AddAxiom aa = new AddAxiom(wholeSchema, ax);
                manager.applyChange(aa);
            }

            sentencenumber++;
        }


        SaveOntology.saveOntologyAsRDFXML(wholeSchema, outputFolder + "/nlp2rdf.lod2.eu.schema.owl");


    }

    /*
    * try {
            OutputStream out = new FileOutputStream("all"+sentencenumber+".owl");

            sentenceModel.writeAll(out, Constants.RDFXML, null);
            SaveOntology.saveAsRDFXML(sentenceModel, "failed"+sentencenumber+".owl");
            SaveOntology.saveAsRDFXML(sentenceModel.getBaseModel(), "base"+sentencenumber+".owl");



            System.out.println(sentenceModel);

               // o = MaterializeModel.convertToInferredModel(o);
            } catch (Exception e) {
            logger.info("ontology for sentence "+sentencenumber+" could not be materialized");
            }
    * */

    private static List<OneLine> getNextSentence(BufferedReader reader) {
        List<OneLine> lines = new ArrayList<OneLine>();
        try {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("#BOS")) {
                    continue;
                }
                if (line.startsWith("#EOS")) {
                    return lines;
                }
                // System.out.println(line+"|");
                StringTokenizer st = new StringTokenizer(line, "\t");
                // System.out.println(st.hasMoreTokens()+"");
                OneLine ol = new OneLine();
                try {
                    String word = st.nextToken();
                    ol.word = word.trim();
                    ol.lemma = st.nextToken().trim();
                    ol.tag = st.nextToken().trim();
                    ol.morph = st.nextToken().trim();
                    ol.edge = st.nextToken().trim();
                    ol.parent = st.nextToken().trim();
                    ol.secedge = (st.hasMoreTokens()) ? st.nextToken() : "";
                    ol.secedge = ol.secedge.trim();
                    ol.comment = (st.hasMoreTokens()) ? st.nextToken() : "";
                    ol.comment = ol.comment.trim();
                } catch (Exception e) {
                    logger.debug("line reading failed:" + line + e.toString());
                    // e.printStackTrace();
                }

                lines.add(ol);

            }
        } catch (Exception e) {
            logger.warn("", e);

        }
        return null;

    }

    private static BufferedReader getReader(File f) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String line;
            do {
                line = reader.readLine();
                if (line == null || (line.contains("word") && line.contains("lemma") && line.contains("morph") && line.contains("edge") && line.contains("parent") && line.contains("secedge") && line.contains("comment"))) {
                    break;
                }
            } while (true);

        } catch (FileNotFoundException e) {

            logger.error("Could not read tiger from: " + f, e);
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reader;

    }

}
