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

package org.nlp2rdf.ontology.olia;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.RDFS;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.nlp2rdf.ontology.ClassIndexer;
import org.nlp2rdf.ontology.OntologyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 */
public class OLiAOntology {
    private static Logger log = LoggerFactory.getLogger(OLiAOntology.class);
    public static String hasTagURI = "http://purl.org/olia/system.owl#hasTag";
    public static String hasTagStartingWithURI = "http://purl.org/olia/system.owl#hasTagStartingWith";

    private final String ontologyUrl;
    private final OntModel model;
    private final ClassIndexer classIndexer = new ClassIndexer();
    private final Map<String, Individual> tagToIndividualMap = new HashMap<String, Individual>();
    private final Map<String, Individual> tagStartingWithToIndividualMap = new HashMap<String, Individual>();
    private final DatatypeProperty hasTag;
    private final DatatypeProperty hasTagStartingWith;

    public OLiAOntology(String ontologyUrl, OntologyLoader ontologyLoader) {
        this.ontologyUrl = ontologyUrl;
        this.model = ontologyLoader.loadOntology(ontologyUrl, PelletReasonerFactory.THE_SPEC);

        ontologyLoader.loadImports(model);
        log.info("finished loading imports");
        classIndexer.index(model);
        log.info("finished indexing");

        hasTag = model.getDatatypeProperty(hasTagURI);
        assert (hasTag != null) : "hasTag was null";
        hasTagStartingWith = model.getDatatypeProperty(hasTagStartingWithURI);
        assert (hasTag != null) : "hasTag was null";

        fillMaps();
    }


    public String getIndividualURIForTag(String tag) {
        Individual ind = tagToIndividualMap.get(tag);
        if (ind == null) {
            log.error("Individual for tag " + tag + " not found returning null, this indicates a mismatch between OLiA and the annotations actually used: " + ontologyUrl);
            return null;
        } else {
            return ind.getURI();
        }
    }

    public Individual getIndividualURIForTagStartingWith(String tag) {
        for (String s : tagStartingWithToIndividualMap.keySet()) {

            if (tag.startsWith(s)) {
                //log.error(tagStartingWithToIndividualMap.toString());
                Individual ind = tagStartingWithToIndividualMap.get(s);
                //log.error(ind.toString());
                return ind;
            }
        }

        return null;
    }

    public Individual getOLiAIndividualForTag(String tag) {
        Individual ind = tagToIndividualMap.get(tag);
        if (ind == null) {
            ind = getIndividualURIForTagStartingWith(tag);
        }
        return ind;

    }

    public Set<String> getClassURIsForTag(String tag) {
        Individual ind = getOLiAIndividualForTag(tag);
        if (ind == null) {
            log.error("Tag: " + tag + " is matching Individual: " + ind);
            return new HashSet<String>();
        } else {
            Set<String> s = new HashSet<String>();
            for (ExtendedIterator<OntClass> it = ind.listOntClasses(true); it.hasNext(); ) {
                OntClass oc = it.next();
                if (!oc.isAnon()) {
                    s.add(oc.getURI());
                    log.debug("found class " + oc + " for ind: " + ind);
                } else {
                    log.debug("skipping anon " + oc + " for ind: " + ind);
                }
            }
            log.info("returning classes: " + s);
            return s;
        }
    }


    public OntModel getHierarchy(String classURI) {
        return classIndexer.getHierarchyForClassURI(classURI);
    }

    private void fillMaps() {
        StringBuilder debug = new StringBuilder();
        debug.append(ontologyUrl + " - Filling Tag2Uri Map\n");

        //get all individuals of the annotation model
        for (ExtendedIterator<Individual> it = model.listIndividuals(); it.hasNext(); ) {
            Individual ind = it.next();


            if (ind.isAnon()) {
                log.error(ind + " was a blanknode, SKIPPING it");
                continue;
            }

            //get the tag (should be exactly one)
            List<RDFNode> l = ind.listPropertyValues(hasTag).toList();
            if (l.size() == 1) {
                Literal tag = l.get(0).asLiteral();
                Individual previous = null;
                //put it in the map, check, if there is something in there already
                if ((previous = tagToIndividualMap.put(tag.getLexicalForm(), ind)) != null) {
                    //then something smells
                    log.error(ind.toString() + " has an ambigue tag: " + tag.toString() + " previous " + previous.getURI() + " " + ontologyUrl);
                }

                debug.append("[ " + tag.getLexicalForm() + " -> " + ind.getURI() + " ]\n");
            } else if (l.size() > 1) {
                //then something smells
                log.error(ind.toString() + " has not exactly one " + hasTag + " property value, but " + l.size() + " " + ontologyUrl);
                for (RDFNode n : l) {
                    log.error("" + n);
                }
            }

            //do it for startingWith
            l = ind.listPropertyValues(hasTagStartingWith).toList();
            if (l.size() == 1) {
                Literal tag = l.get(0).asLiteral();
                Individual previous = null;
                //put it in the map, check, if there is something in there already
                if ((previous = tagStartingWithToIndividualMap.put(tag.getLexicalForm(), ind)) != null) {
                    //then something smells
                    log.error(ind.toString() + " has an ambigue tagStartingWith: " + tag.toString() + " previous " + previous.getURI() + " " + ontologyUrl);
                }

                debug.append("[ " + tag.getLexicalForm() + " -> " + ind.getURI() + " ]\n");
            } else if (l.size() > 1) {
                //then something smells
                log.error(ind.toString() + " has not exactly one " + hasTagStartingWith + " property value, but " + l.size() + " " + ontologyUrl);
                for (RDFNode n : l) {
                    log.error("" + n);
                }
            }


        }
        log.trace(debug.toString());
        log.debug("total tags: " + tagToIndividualMap.size());
    }

    public static void classHierarchy2PropertyHierarchy(OntModel hierarchy, OntModel out, String allowedNamespace) {
        for (StmtIterator stm = hierarchy.listStatements(null, RDFS.subClassOf, (RDFNode) null); stm.hasNext(); ) {
            Statement current = stm.nextStatement();
            Resource subject = current.getSubject();

            if (!(subject.getURI().startsWith(allowedNamespace))) {
                continue;
            }
            if (subject.isAnon()) {
                continue;
            }
            if (current.getObject().isAnon()) {
                continue;
            }
            String objectURI = current.getObject().asResource().getURI();
            if (!(objectURI.startsWith(allowedNamespace))) {
                continue;
            }
            ObjectProperty op = out.createObjectProperty(subject.getURI());
            op.addSuperProperty(out.createObjectProperty(objectURI));
        }
    }

    public String getOntologyUrl() {
        return ontologyUrl;
    }

    public Map<String, Individual> getTagToIndividualMap() {
        return tagToIndividualMap;
    }
}
