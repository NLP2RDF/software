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

package org.nlp2rdf.ontology;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.impl.OntModelImpl;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 *         Created: 29.06.11
 *         <p/>
 *         //TODO there is some domain specific replacements  in this class
 *         private String toClasspath(String ontologyUrl) throws MalformedURLException{
 *         ontologyUrl = ontologyUrl.replace("http://nlp2rdf.lod2.eu/schema/sso/", "http://nlp2rdf.lod2.eu/schema/sso/sso.owl");
 *         ontologyUrl = ontologyUrl.replace("http://nlp2rdf.lod2.eu/schema/string/", "http://nlp2rdf.lod2.eu/schema/string/string.owl");
 */
public class ClasspathLoader implements OntologyLoader {
    private static Logger log = LoggerFactory.getLogger(ClasspathLoader.class);

    public static void main(String[] args) throws Exception {
        String test = "http://nlp2rdf.lod2.eu/schema/sso/";
        System.out.println(new ClasspathLoader().loadOntology(test));
    }

    public class CC extends OntModelImpl {
        public CC(OntModelSpec spec, Model model) {
            super(spec, model);
        }
    }

    @Override
    public OntModel loadOntology(String ontologyUri) {
        return loadOntology(ontologyUri, OntModelSpec.OWL_DL_MEM);
    }

    @Override
    public OntModel loadOntology(String ontologyUri, OntModelSpec spec) {
        OntModel model = ModelFactory.createOntologyModel(spec);
        model.getDocumentManager().setProcessImports(false);
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(toClasspath(ontologyUri));
            model.read(is, "");
            log.info("ontology " + ontologyUri + " was loaded from classpath. ");
        } catch (MalformedURLException e) {
            log.error("" + ontologyUri, e);
        } catch (IOException e) {
            log.warn("ontology was not in classpath, loading from url" + ontologyUri, e);
            log.error("exit for development");
            System.exit(0);
            model.read(ontologyUri);
        }
        return model;
    }


    @Override
    public void loadImports(OntModel model) {
        loadImports(model, new HashSet<String>());
    }


    private void loadImports(OntModel m, Set<String> loaded) {
        for (String one : m.listImportedOntologyURIs()) {
            log.debug("adding one more: " + one);
            if (loaded.add(one) == false) {
                log.debug("skipping " + one + " (already loaded)");
                continue;
            }
            OntModel sm = loadOntology(one);
            loadImports(sm);
            m.addSubModel(sm);
        }
    }


    public String toClasspath(String ontologyUrl) throws MalformedURLException {

        ontologyUrl = ontologyUrl.replace("http://nlp2rdf.lod2.eu/schema/sso/", "http://nlp2rdf.lod2.eu/schema/sso/sso.owl");
        ontologyUrl = ontologyUrl.replace("http://nlp2rdf.lod2.eu/schema/string/", "http://nlp2rdf.lod2.eu/schema/string/string.owl");

        StringBuilder sb = new StringBuilder();

        URL u = new URL(ontologyUrl);

        //reverse host
        String host = u.getHost();
        for (String s : host.split("\\.")) {
            sb.insert(0, s + "/");
        }

        //sb.insert(0, "classpath:");

        //get path
        sb.append(u.getPath());

        return sb.toString().replace("//", "/");

    }

}
