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

package org.nlp2rdf.implementation.opennlp;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import eu.lod2.nlp2rdf.schema.Thing;
import eu.lod2.nlp2rdf.schema.sso.Sentence;
import eu.lod2.nlp2rdf.schema.sso.Word;
import eu.lod2.nlp2rdf.schema.tools.Factory;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import org.nlp2rdf.core.Span;
import org.nlp2rdf.core.Text2RDF;
import org.nlp2rdf.core.URIGenerator;
import org.nlp2rdf.core.util.URIComparator;
import org.nlp2rdf.core.urischemes.URIGeneratorHelper;
import org.nlp2rdf.ontology.olia.OLiAManager;
import org.nlp2rdf.ontology.olia.OLiAOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class OpenNLPWrapper {

    private static Logger log = LoggerFactory.getLogger(OpenNLPWrapper.class);


    private static POSModel posmodel = null;

    private final POSTaggerME posTaggerME;
    private final OLiAOntology penn;
    private final OpenNLPTokenizer openNLPTokenizer;

    static {
        /***************************
         * Important requirement...
         */
        Factory.registerCustomClasses();
    }

    public OpenNLPWrapper(OLiAManager oLiAManager) {
        penn = oLiAManager.getOLiAOntology("http://purl.org/olia/penn-link.rdf");
        posTaggerME = new POSTaggerME(getPOSModel());
        openNLPTokenizer = new OpenNLPTokenizer();
    }

    public static void main(String[] args) throws Exception {
        try {
            File input = null;
            String outfile = null;
            String format = null;
            String urirecipe = null;
            String prefix = null;

            // Read and validate command line arguments
            boolean validArgs = false;
            if (args.length == 3) {
                input = new File(args[1]);
                urirecipe = args[2];
                prefix = args[3];
                validArgs = input.exists() && !input.isDirectory();
                validArgs = validArgs && (urirecipe.equalsIgnoreCase("offset") || urirecipe.equalsIgnoreCase("context-hash"));
            }
            if (!validArgs) {
                printUsageMessage();
            } else {
                if (!input.isDirectory()) {
                    OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
                    OpenNLPWrapper wrap = new OpenNLPWrapper(new OLiAManager());
                    log.info("Processing file " + input);
                    String document = wrap.readFileAsString(input);
                    document = document.trim();
                    //TODO parameter contextLength instead of 10
                    URIGenerator uriGenerator = URIGeneratorHelper.determineGenerator(urirecipe, 10);
                    wrap.processText(prefix, document, uriGenerator, ontModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Prints usage message.
     */
    private static void printUsageMessage() {
        System.err.println("Usage: java org.nlp2rdf.wrapper.opennlp.OpenNLPWrapper "
                + "<input file> <output filename> <urirecipe> <prefix>");
    }

    private String readFileAsString(File source) throws IOException {
        final DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(source)));
        final byte[] buffer = new byte[(int) source.length()];
        dis.readFully(buffer);
        dis.close();
        return new String(buffer);
    }

    public void processText(String prefix, String text, URIGenerator urigenerator, OntModel model) {

        TreeMap<Span, List<Span>> tokenizedText = openNLPTokenizer.tokenizeText(text);

        //URIGenerator urigenerator = URIGeneratorHelper.initURIGenerator(text, tokenizedText, urirecipe);


        Text2RDF text2RDF = new Text2RDF();
        Individual context = text2RDF.createDocumentAnnotation(prefix, text, urigenerator, model);
        text2RDF.generateNIFModel(prefix, text, tokenizedText, urigenerator, context, model);
        assignPosTags(prefix, text, urigenerator, model);

        //add additional data
        text2RDF.addNextAndPreviousProperties(prefix, text, urigenerator, model);
        //here OLiA classes are added
        text2RDF.addCopyOfOLiAClassesAndHierarchy(penn, model);
    }


    public void processNIFModel(String prefix, String text, String urirecipe, OntModel model) {

        //Text2RDF text2RDF = new Text2RDF();
        //URIGenerator uriGenerator = URIGeneratorHelper.determineGenerator(urirecipe);
        //text2RDF.getTokenization(prefix,text, )
        //URIGenerator urigenerator = URIGeneratorHelper.initURIGenerator(text, tokenizedText, urirecipe);

    }

    public void assignPosTags(String prefix, String text, URIGenerator uriGenerator, OntModel model) {
        List<Sentence> sentences = Sentence.list(model);
        for (int x = 0; x < sentences.size(); x++) {
            Sentence sentence = sentences.get(x);
            List<Word> words = sentence.listWord();
            Collections.sort(sentences, new URIComparator(prefix, text, uriGenerator));
            String[] tokens = new String[words.size()];
            for (int i = 0; i < words.size(); i++) {
                tokens[i] = words.get(i).getAnchorOf();
            }
            String postags[] = posTaggerME.tag(tokens);
            for (int i = 0; i < words.size(); i++) {
                words.get(i).addPosTag(postags[i]);
                String oliaIndividual = null;
                if ((oliaIndividual = penn.getIndividualURIForTag(postags[i])) != null) {
                    words.get(i).addOliaLink(Thing.create(oliaIndividual, model));
                }
            }
        }
    }

    private POSModel getPOSModel() {
        if (posmodel == null) {
            try {
                InputStream modelIn = this.getClass().getClassLoader().getResourceAsStream(OpenNLPTokenizer.RESOURCEPATH + "en-pos-maxent.bin");
                try {
                    posmodel = new POSModel(modelIn);
                } finally {
                    modelIn.close();
                }
            } catch (IOException e) {
                log.error("", e);
            }
        }

        return posmodel;
    }


}
