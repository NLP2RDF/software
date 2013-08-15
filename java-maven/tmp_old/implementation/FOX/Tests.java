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


import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.io.ByteArrayInputStream;

public class Tests {
    public static void testFoxOutput() {
        String ner = "@prefix scmsann:  <http://ns.aksw.org/scms/annotations/> .\n" + "@prefix ctag:    <http://commontag.org/ns#> .\n" + "@prefix xsd:     <http://www.w3.org/2001/XMLSchema#> .\n" + "@prefix rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" + "@prefix ann:     <http://www.w3.org/2000/10/annotation-ns#> .\n" + "@prefix scms:    <http://ns.aksw.org/scms/> .\n" + "\n" + "[]    a       ann:Annotation , scmsann:LOCATION ;\n" + "      scms:beginIndex \"36\"^^xsd:int , \"206\"^^xsd:int ;\n" + "      scms:endIndex \"213\"^^xsd:int , \"43\"^^xsd:int ;\n" + "      scms:means <http://dbpedia.org/resource/Leipzig> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/FOX> ;\n" + "      ann:body \"Leipzig\"^^xsd:string .\n" + "\n" + "[]    a       scmsann:PERSON , ann:Annotation ;\n" + "      scms:beginIndex \"176\"^^xsd:int ;\n" + "      scms:endIndex \"193\"^^xsd:int ;\n" + "      scms:means <http://dbpedia.org/resource/Gottfried_Leibniz> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/FOX> ;\n" + "      ann:body \"Gottfried Leibniz\"^^xsd:string .\n" + "\n" + "[]    a       ann:Annotation , scmsann:ORGANIZATION ;\n" + "      scms:beginIndex \"22\"^^xsd:int ;\n" + "      scms:endIndex \"43\"^^xsd:int ;\n" + "      scms:means <http://dbpedia.org/resource/University_of_Leipzig> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/FOX> ;\n" + "      ann:body \"University of Leipzig\"^^xsd:string .\n" + "\n" + "\n";
        String ke = "@prefix scmsann:  <http://ns.aksw.org/scms/annotations/> .\n" + "@prefix ctag:    <http://commontag.org/ns#> .\n" + "@prefix xsd:     <http://www.w3.org/2001/XMLSchema#> .\n" + "@prefix rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" + "@prefix ann:     <http://www.w3.org/2000/10/annotation-ns#> .\n" + "@prefix scms:    <http://ns.aksw.org/scms/> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"Leibniz\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/5149_Leibniz> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"german law\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Early_Germanic_law> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"mathematician\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Mathematician> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"German law\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Early_Germanic_law> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"mathematician\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Mathematician> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"Leipzig\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Leipzig> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"publishing industry\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Publishing_industry_in_China> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"philosopher\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Philosophes> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"university of leipzig\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/University_of_Leipzig> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"Universities\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/IT_University> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperKeaKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"gottfried leibniz\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Gottfried_Leibniz> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "\n";
        String text = "The foundation of the University of Leipzig in 1409 initiated the city's development into a centre of German law and the publishing industry. The philosopher and mathematician Gottfried Leibniz was born in Leipzig in 1646, and attended the university from 1661–1666.";

        Model in = ModelFactory.createDefaultModel();
        in.read(new ByteArrayInputStream(ner.getBytes()), "", "TURTLE");

        Model ret = new FOXOutputConverter().toNif(in, text);
        ret.write(System.out, "TURTLE");

        ner = "@prefix scmsann:  <http://ns.aksw.org/scms/annotations/> .\n" + "@prefix ctag:    <http://commontag.org/ns#> .\n" + "@prefix xsd:     <http://www.w3.org/2001/XMLSchema#> .\n" + "@prefix rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" + "@prefix ann:     <http://www.w3.org/2000/10/annotation-ns#> .\n" + "@prefix scms:    <http://ns.aksw.org/scms/> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"Leibniz\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/5149_Leibniz> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"german law\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Early_Germanic_law> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"mathematician\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Mathematician> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"German law\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Early_Germanic_law> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"mathematician\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Mathematician> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"Leipzig\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Leipzig> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"publishing industry\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Publishing_industry_in_China> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperAlchemyKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"philosopher\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Philosophes> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"university of leipzig\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/University_of_Leipzig> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"Universities\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/IT_University> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperKeaKE> .\n" + "\n" + "[]    a       ctag:AutoTag ;\n" + "      ctag:label \"gottfried leibniz\"^^xsd:string ;\n" + "      ctag:means <http://dbpedia.org/resource/Gottfried_Leibniz> ;\n" + "      scms:source <http://ns.aksw.org/scms/tools/WrapperYahooapisKE> .\n" + "\n" + "\n";

        in = ModelFactory.createDefaultModel();
        in.read(new ByteArrayInputStream(ner.getBytes()), "", "TURTLE");

        ret = new FOXOutputConverter().toNif(in, text);
        ret.write(System.out, "TURTLE");

    }
}
