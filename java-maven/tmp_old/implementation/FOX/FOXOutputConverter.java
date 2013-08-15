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

package org.nlp2rdf.implementation;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.vocabulary.RDF;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 *         Created: 08.07.11
 *         <p/>
 *         This class provides static functions to convert the SCMS Jena model to the NIF Format
 */
public class FOXOutputConverter {

    protected static Logger log = Logger.getRootLogger();

    public static final String nsAnn = "http://www.w3.org/2000/10/annotation-ns#";
    public static final String nsCTag = "http://commontag.org/ns#";
    public static final String nsScms = "http://ns.aksw.org/scms/";
    public static final String nsScmsann = "http://ns.aksw.org/scms/annotations/";
    public static String nsString = "http://nlp2rdf.lod2.eu/schema/string/";
    public static String nsSSO = "http://nlp2rdf.lod2.eu/schema/sso/";

    public OntModel toNif(Model model, String text) {
        return toNif(model, text, "http://ns.aksw.org/scms/tools/FOX/");
    }

    /**
     * see http://aksw.org/Projects/NIF
     *
     * @param model  the model to be transformed to NIF
     * @param text   the whole text of the document
     * @param prefix the prefix for the NIF URIs (see http://aksw.org/Projects/NIF)
     * @return a NIF model
     */
    public OntModel toNif(Model model, String text, String prefix) {
        OntModel ret = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        ret.setNsPrefix("str", nsString);
        ret.setNsPrefix("sso", nsSSO);
        ret.setNsPrefix("scms", nsScms);
        ret.setNsPrefix("ann", nsAnn);
        ret.setNsPrefix("ctag", nsCTag);
        String documentUri = prefix + "offset_" + 0 + "_" + (text.length()) + "_" + getFirstCharacters(text, 20);
        Individual document = ret.createIndividual(documentUri, ret.createResource(nsString + "Document"));
        document.addProperty(ret.createProperty(nsString + "sourceString"), text);

        /*
        Get all properties used by NIF
        */
        Property beginIndexProperty = ret.createProperty(nsScms + "beginIndex");
        Property endIndexProperty = ret.createProperty(nsScms + "endIndex");
        Property anchorOfProperty = ret.createProperty(nsString + "anchorOf");
        Property meansProperty = ret.createProperty(nsScms + "means");
        Property sourceProperty = ret.createProperty(nsScms + "source");

        Property ctagLabelProperty = ret.createProperty(nsCTag + "label");
        Property ctagMeansProperty = ret.createProperty(nsCTag + "means");
        Property ctagTaggedProperty = ret.createProperty(nsCTag + "tagged");

        Property subStringProperty = ret.createProperty(nsString + "subString");

        /*        []    a       ctag:AutoTag ;
            ctag:label "german law"^^xsd:string ;
            ctag:means <http://dbpedia.org/resource/Early_Germanic_law> ;
            scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .
        */

        //get all KE annotations
        for (ResIterator i = model.listSubjectsWithProperty(RDF.type, model.getResource(nsCTag + "AutoTag")); i.hasNext(); ) {
            Resource r = i.nextResource();

            //get all info
            RDFNode ctaglabel = r.getProperty(ctagLabelProperty).getObject();
            RDFNode means = r.getProperty(ctagMeansProperty).getObject();
            RDFNode source = r.getProperty(sourceProperty).getObject();

            Resource tag = ret.createResource().addProperty(ctagLabelProperty, ctaglabel).addProperty(ctagMeansProperty, means).addProperty(sourceProperty, source).addProperty(RDF.type, ret.createResource(nsCTag + "AutoTag"));
            document.addProperty(ctagTaggedProperty, tag);

        }

        /*      []    a       ann:Annotation , scmsann:ORGANIZATION ;
                scms:beginIndex "22"^^xsd:int ;
                scms:endIndex "43"^^xsd:int ;
                scms:means <http://dbpedia.org/resource/University_of_Leipzig> ;
                scms:source <http://ns.aksw.org/scms/tools/FOX> ;
                ann:body "University of Leipzig"^^xsd:string .
        */
        //get all NE annotations
        for (ResIterator i = model.listSubjectsWithProperty(RDF.type, model.getResource(nsAnn + "Annotation")); i.hasNext(); ) {
            Resource r = i.nextResource();
            for (Statement stmt : r.listProperties(model.getProperty(nsScms + "beginIndex")).toList()) {
                int beginIndex = stmt.getInt();
                String anchorOf = r.getProperty(model.getProperty(nsAnn + "body")).getString();
                int endIndex = (beginIndex + anchorOf.length());
                //make a NIF URI
                String uri = prefix + "offset_" + beginIndex + "_" + (endIndex) + "_" + getFirstCharacters(anchorOf, 20);

                RDFNode means = r.getProperty(meansProperty).getObject();
                RDFNode source = r.getProperty(sourceProperty).getObject();

                //map to NIF Properties
                Individual occurence = ret.createIndividual(uri, ret.createResource(nsString + "String"));
                occurence.addOntClass(ret.createResource(nsString + "OffsetBasedString"));
                occurence.addLiteral(beginIndexProperty, model.createTypedLiteral(beginIndex));
                occurence.addLiteral(endIndexProperty, model.createTypedLiteral(endIndex));
                occurence.addProperty(anchorOfProperty, anchorOf);

                occurence.addProperty(meansProperty, means);
                occurence.addProperty(sourceProperty, source);

                //classes
                for (StmtIterator i2 = r.listProperties(RDF.type); i2.hasNext(); ) {
                    RDFNode n = i2.nextStatement().getObject();
                    occurence.addProperty(RDF.type, n);
                }

                //add substring of document
                document.addProperty(subStringProperty, occurence);

            }

            //get all infos
            //int endIndex = r.getProperty(model.getProperty(nsScms + "endIndex")).getInt();

        }
        return ret;
    }

    /**
     * return the first characters of the anchored part urlencoded
     *
     * @param anchoredPart
     * @param firstCharLength
     * @return
     */
    public static String getFirstCharacters(String anchoredPart, int firstCharLength) {
        String firstChars = "";
        try {
            firstChars = URLEncoder.encode((anchoredPart.length() > firstCharLength) ? anchoredPart.substring(0, firstCharLength) : anchoredPart, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("", e);
        }
        return firstChars;
    }

    /*
  sb = new StringBuilder();
         sb.append("PREFIX scms: <http://ns.aksw.org/scms/> \nPREFIX  ann: <http://www.w3.org/2000/10/annotation-ns#>\nPREFIX str: <http://nlp2rdf.lod2.eu/schema/string/> \n");
         sb.append("CONSTRUCT { ");
         sb.append("<").append(uri).append("> a ?class . ");
         sb.append("<").append(uri).append("> a str:String . ");
         sb.append("<").append(uri).append("> str:beginIndex  ?beginIndex . ");
         sb.append("<").append(uri).append("> str:endIndex  ?endIndex . ");
         sb.append("<").append(uri).append("> scms:means  ?means . ");
         sb.append("<").append(uri).append("> scms:source  ?source . ");
         sb.append("<").append(uri).append("> str:anchorOf  ?body . ");
         sb.append(" } { ");

         sb.append("<").append(r.getURI()).append(">  a ?class . ");
         sb.append("?s  scms:beginIndex  ?beginIndex . ");
         sb.append("?s  scms:endIndex  ?endIndex . ");
         sb.append("?s  scms:means  ?means . ");
         sb.append("?s  scms:source  ?source . ");
         sb.append("?s  ann:body  ?body . ");
         sb.append(" } ");
         System.out.println(sb.toString());
         QueryExecution que = QueryExecutionFactory.create(sb.toString(), model);
         ret.add(que.execConstruct());

    */

    public static void main(String[] args) {
        String ner = "@prefix scmsann:  <http://ns.aksw.org/scms/annotations/> .\n" + "@prefix ctag:    <http://commontag.org/ns#> .\n" + "@prefix xsd:     <http://www.w3.org/2001/XMLSchema#> .\n" + "@prefix rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" + "@prefix ann:     <http://www.w3.org/2000/10/annotation-ns#> .\n" + "@prefix scms:    <http://ns.aksw.org/scms/> .\n" + "\n" + "[]    a       ann:Annotation , scmsann:LOCATION ;\n" + "      scms:beginIndex \"36\"^^xsd:int , \"206\"^^xsd:int ;\n" + "      scms:endIndex \"213\"^^xsd:int , \"43\"^^xsd:int ;\n" + "      scms:means <http://dbpedia.org/resource/Leipzig> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/FOX> ;\n" + "      ann:body \"Leipzig\"^^xsd:string .\n" + "\n" + "[]    a       scmsann:PERSON , ann:Annotation ;\n" + "      scms:beginIndex \"176\"^^xsd:int ;\n" + "      scms:endIndex \"193\"^^xsd:int ;\n" + "      scms:means <http://dbpedia.org/resource/Gottfried_Leibniz> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/FOX> ;\n" + "      ann:body \"Gottfried Leibniz\"^^xsd:string .\n" + "\n" + "[]    a       ann:Annotation , scmsann:ORGANIZATION ;\n" + "      scms:beginIndex \"22\"^^xsd:int ;\n" + "      scms:endIndex \"43\"^^xsd:int ;\n" + "      scms:means <http://dbpedia.org/resource/University_of_Leipzig> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/FOX> ;\n" + "      ann:body \"University of Leipzig\"^^xsd:string .\n" + "\n" + "\n";
        String ke = "@prefix scmsann:  <http://ns.aksw.org/scms/annotations/> .\n" + "@prefix ctag:    <http://commontag.org/ns#> .\n" + "@prefix xsd:     <http://www.w3.org/2001/XMLSchema#> .\n" + "@prefix rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" + "@prefix ann:     <http://www.w3.org/2000/10/annotation-ns#> .\n" + "@prefix scms:    <http://ns.aksw.org/scms/> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"Leibniz\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/5149_Leibniz> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"german law\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Early_Germanic_law> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"mathematician\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Mathematician> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"German law\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Early_Germanic_law> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"mathematician\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Mathematician> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"Leipzig\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Leipzig> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"publishing industry\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Publishing_industry_in_China> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"philosopher\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Philosophes> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"university of leipzig\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/University_of_Leipzig> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"Universities\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/IT_University> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperKeaKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"gottfried leibniz\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Gottfried_Leibniz> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "\n";
        String text = "The foundation of the University of Leipzig in 1409 initiated the city's development into a centre of German law and the publishing industry. The philosopher and mathematician Gottfried Leibniz was born in Leipzig in 1646, and attended the university from 1661–1666.";

        //test(ner, text);
        test(ke, text);

    }

    public static void test(String rdf, String text) {
        Model in = ModelFactory.createDefaultModel();
        in.read(new ByteArrayInputStream(rdf.getBytes()), "", "TURTLE");

        Model ret = new FOXOutputConverter().toNif(in, text);
        ret.write(System.out, "TURTLE");

    }

}
