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

package org.nlp2rdf;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.OWL;
import org.nlp2rdf.ontology.olia.OLiAManager;
import org.nlp2rdf.ontology.olia.OLiAOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * this class should actually be in utilities, but this would cause a cyclic dependency to generated classes
 */
public class MappingGenerator {
    private static Logger log = LoggerFactory.getLogger(MappingGenerator.class);

    public static String hasTagURI = "http://purl.org/olia/system.owl#hasTag";

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        OLiAManager om = new OLiAManager();
        List<OLiAOntology> l = new ArrayList<OLiAOntology>();
        l.add(om.getOLiAOntology("http://purl.org/olia/brown-link.rdf"));
        l.add(om.getOLiAOntology("http://purl.org/olia/penn-link.rdf"));
        l.add(om.getOLiAOntology("http://purl.org/olia/penn-syntax-link.rdf"));
        l.add(om.getOLiAOntology("http://purl.org/olia/stanford.owl"));


        String path = "oliaOntologies/";

        for (OLiAOntology o : l) {
            String currentPath = path + URLEncoder.encode(o.getOntologyUrl(), "UTF-8");
            new File(currentPath).mkdirs();
            Set<String> s = o.getTagToIndividualMap().keySet();
            for (String tag : s) {

                OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
                String ind = o.getIndividualURIForTag(tag);
                if (ind == null) {
                    log.info("tag not found (SKIPPING): " + tag);
                    continue;
                }
                m.createIndividual(ind, OWL.Thing);

                Set<String> classUris = o.getClassURIsForTag(tag);
                for (String classUri : classUris) {
                    m.add(o.getHierarchy(classUri));
                    for (Iterator<OntClass> it = m.listClasses(); it.hasNext(); ) {
                        OntClass c = it.next();
                        c.addRDFType(OWL.Class);
                    }

                }

                String filepart = URLEncoder.encode(tag, "UTF-8");
                /*
                * Special Handling
                * */
                filepart = filepart.replaceAll("\\.", "_");
                String filename = currentPath + "/" + filepart;


                // rdf/xml
                FileWriter fstream = new FileWriter(filename+".rdf");
                m.write(fstream, "RDF/XML");
                fstream.close();
                //turtle
                fstream = new FileWriter(filename+".ttl");
                m.write(fstream, "N3");
                fstream.close();
            }
        }


    }

}
