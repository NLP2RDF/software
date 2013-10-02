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

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.DC;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.urischemes.URIScheme;
import org.nlp2rdf.core.vocab.NIFDatatypeProperties;
import org.nlp2rdf.implementation.stanfordcorenlp.StanfordWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.*;


/**
 * The basic code was taken from the ClearTK Project
 * http://code.google.com/p/cleartk
 * who have written a UIMA wrapper.
 * The original file by Steven Bethard can be found here:
 * http://code.google.com/p/cleartk/source/browse/trunk/cleartk-stanford-corenlp/src/main/java/org/cleartk/stanford/StanfordCoreNLPAnnotator.java
 * Licence http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * <p/>
 * Debug with  echo -n "This is a sentence." | mvn compile exec:java -e  -Dexec.mainClass="org.nlp2rdf.implementation.stanfordcore.StanfordCoreCLI" -Dexec.args="-f text -i -" | less
 *
 * @author Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 */

public class LExO {
    private static Logger log = LoggerFactory.getLogger(LExO.class);
    private static StanfordWrapper stanfordWrapper = new StanfordWrapper();

    private static OntModel nifmodel = null;
    private static List<String> queries = null;
    private static final String sparqlPrefix = "PREFIX lexo: <http://example.org/lexo#> \n" +
            "PREFIX stanford: <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/dep/stanford#> \n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
            "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
            "PREFIX olia: <http://purl.org/olia/olia.owl#> \n" +
            "PREFIX nif: <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#> \n";

    private static String lexons = "http://example.org/lexo#";
    private static String lexotypens = "http://example.org/lexotype#";

    private synchronized List<String> getQueries() {
        if (queries == null) {
            queries = new ArrayList<String>();
            OntModel suite = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
            InputStream aa = LExO.class.getClassLoader().getResourceAsStream("preprocess.ttl");
            suite.read(aa, "", "N3");
            String lexp = "http://example.org/lexo#";
            ExtendedIterator<Individual> eit = suite.listIndividuals(suite.createClass(lexp + "Preprocessing"));
            Individual current = null;
            for (; eit.hasNext(); ) {
                current = eit.next();
                queries.add(sparqlPrefix + current.getPropertyValue(suite.createDatatypeProperty(lexp + "query")).toString());
            }
        }

        return queries;

    }

    synchronized OntModel getNIFModel() {
        if (nifmodel == null) {

            String nif_core_owl = "org/uni-leipzig/persistence/nlp2rdf/nif-core/nif-core.owl";
            String nif_core_inf_owl = "org/uni-leipzig/persistence/nlp2rdf/nif-core/nif-core-inf.owl";
            InputStream is1 = LExO.class.getClassLoader().getResourceAsStream(nif_core_owl);
            InputStream is2 = LExO.class.getClassLoader().getResourceAsStream(nif_core_inf_owl);
            nifmodel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
            nifmodel.createAnnotationProperty(DC.description.getURI());
            nifmodel.read(is1, "", "RDF/XML");
            nifmodel.read(is2, "", "RDF/XML");
        }
        return nifmodel;
    }

    public void processText(String prefix, Individual context, URIScheme urischeme, OntModel model, NIFParameters nifParameters) {
        String contextString = context.getPropertyValue(NIFDatatypeProperties.isString.getDatatypeProperty(model)).asLiteral().getString();
        stanfordWrapper.processText(prefix, context, urischeme, model, nifParameters);
        model.addSubModel(getNIFModel());
        model.prepare();


        OntModel intermediate = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());

        for (String query : getQueries()) {
            QueryExecution qe = QueryExecutionFactory.create(query, model);
            qe.execConstruct(intermediate);
        }


        QueryExecution getremaininganchors = QueryExecutionFactory.create(sparqlPrefix + "SELECT ?res ?anchor  {?s nif:dependencyTrans ?res  . ?res nif:anchorOf ?anchor . FILTER (NOT EXISTS {[] stanford:det ?res }) }", model);
        ResultSet rs = getremaininganchors.execSelect();
        Set<Resource> res1 = new HashSet<>();
        Map<Resource, String> anchorOf = new HashMap<>();
        while (rs.hasNext()) {
            QuerySolution qs = rs.next();
            Resource s = qs.getResource("res");
            res1.add(s);
            anchorOf.put(s, qs.getLiteral("anchor").toString());
        }

        int totalIDs = res1.size();
        QueryExecution qe2 = QueryExecutionFactory.create(sparqlPrefix + "SELECT ?s  {?s ?p ?o. FILTER (!isBlank(?s))  } ", intermediate);
        ResultSet rs2 = qe2.execSelect();
        while (rs2.hasNext()) {
            QuerySolution qs = rs2.next();
            Resource s = qs.getResource("s");
            res1.remove(s);
        }

        int covered = totalIDs - res1.size();


        //print debug output
        for (Resource r : res1) {
            System.err.print(anchorOf.get(r));
            System.err.println(" [" + r + "]");
        }

        // remove all "handled" statements  once
        List<Statement> handled = intermediate.listStatements(null, intermediate.createDatatypeProperty(lexons + "handled"), (String) null).toList();
        for (Statement s : handled) {
            intermediate.remove(s);
        }

        compound_names(intermediate);
        intermediate.removeAll(null, model.createObjectProperty(lexons + "compound"), null);

        /**String ready = "Select ?s { ?s ?p ?o . FILTER (strstarts(str(?s),\"" + nifParameters.getPrefix() + "\" )) . " +
         "FILTER ( " +
         "   NOT EXISTS {?s lexo:subclassof ?o . FILTER (strstarts(str(?o), \"" + nifParameters.getPrefix() + "\" )) }   "
         //+ " && NOT EXISTS {?s ?p [ ?p2 ?o2 ] . FILTER (strstarts(str(?o2), \"" + nifParameters.getPrefix() + "\" )) }   "
         + " ) }";
         ready = "Select ?s { ?s ?p ?o .  " +
         "FILTER ( " +
         "   NOT EXISTS {?s lexo:subclassof ?o . FILTER (strstarts(str(?o), \"" + nifParameters.getPrefix() + "\" )) }   "
         //+ " && NOT EXISTS {?s ?p [ ?p2 ?o2 ] . FILTER (strstarts(str(?o2), \"" + nifParameters.getPrefix() + "\" )) }   "
         + " ) }";
         System.out.println(nifParameters.getPrefix());
         System.out.println(sparqlPrefix+ready);

         QueryExecution nextres = QueryExecutionFactory.create(sparqlPrefix + ready, intermediate);
         ResultSet rs3 = nextres.execSelect();

         while (rs3.hasNext()) {
         QuerySolution qs = rs3.next();
         Resource s = qs.getResource("s");

         System.out.println(s);
         //res1.remove(s);
         }  */

        intermediate.write(System.out, "N3");
        System.err.println("Left: " + " " + res1.size() + " of " + totalIDs + ". Coverage: " + ((double) covered / totalIDs));
        System.exit(0);
        /*
      List<Resource> resources = intermediate.listSubjects().toList();
      boolean modified = false;
      while (!resources.isEmpty() && modified) {
          modified = false;
          Set<Resource> remove = new HashSet<>();
          for (Resource r : resources) {
          }
      }  */


        System.exit(0);
    }

    public void handle(Resource r, OntModel model) {
        ObjectProperty name = model.createObjectProperty(lexons + "name");

    }


    public void compound_names(OntModel model) {
        ObjectProperty cn = model.createObjectProperty(lexons + "compound");
        ObjectProperty name = model.createObjectProperty(lexons + "name");
        DatatypeProperty cnOrder = model.createDatatypeProperty(lexons + "cnOrder");
        DatatypeProperty cnName = model.createDatatypeProperty(lexons + "cnName");
        List<Resource> resources = model.listSubjectsWithProperty(cn).toList();

        List<Resource> bns = new ArrayList<>();

        //each resource
        for (Resource r : resources) {
            StmtIterator sit = r.listProperties(cn);
            Resource currentr = null;
            SortedSet<Sorter> sose = new TreeSet<Sorter>();

            while (sit.hasNext()) {
                currentr = sit.nextStatement().getObject().asResource();
                bns.add(currentr);
                sose.add(new Sorter(currentr.getProperty(cnOrder).getObject().asLiteral().getInt(), currentr.getProperty(cnName).getObject().asLiteral().toString()));
            }

            StringBuilder sb = new StringBuilder(lexotypens);
            for (Sorter s : sose) {
                sb.append(s.getName());
                sb.append("_");
            }


            r.addProperty(name, model.createResource(sb.substring(0, sb.length() - 1)));

        }
        for (Resource r : bns) {
            r.removeProperties();
        }
    }


    class Sorter implements Comparable<Sorter> {
        private final int order;
        private final String name;

        Sorter(int order, String name) {
            this.order = order;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Sorter s) {
            return this.order - s.order;
        }
    }

    /**

     while (it1.hasNext()) {
     res1.add(it1.nextStatement().getObject().asResource());
     }

     ResIterator it2 = intermediate.listSubjects();
     Set<Resource> res2 = new HashSet<>();
     while (it2.hasNext()) {
     Resource r = it2.nextResource();
     if (r.isURIResource()) {
     res2.add(r);
     }
     }    **/

    //Set<Resource> difference = new HashSet<Resource>(res1);

    //System.out.println(res1);
    //System.out.println(res2);
    //System.out.println(difference.toString());


    //
    //model.listResourcesWithProperty(NIFObjectProperties.dependencyTrans.getObjectProperty(model));

    /** //
     // System.out.println().toList());
     ObjectProperty deptrans = NIFObjectProperties.dependencyTrans.getObjectProperty(model);
     DatatypeProperty beginIndex = NIFDatatypeProperties.beginIndex.getDatatypeProperty(model);
     AnnotationProperty oliaCategory = NIFAnnotationProperties.oliaCategory.getAnnotationProperty(model);
     DatatypeProperty lexo = model.createDatatypeProperty("http://lexo.le/def");
     DatatypeProperty lemma = NIFDatatypeProperties.lemma.getDatatypeProperty(model);
     WordComparator wc = new WordComparator(beginIndex);


     ResIterator resIterator = model.listResourcesWithProperty(deptrans);
     Set<String> forbidden = new HashSet<>(Arrays.asList(new String[]{"http://purl.org/olia/olia.owl#Determiner"}));

     while (resIterator.hasNext()) {
     SortedSet<Individual> words = new TreeSet<>(wc);
     Resource current = resIterator.nextResource();
     NodeIterator nt = model.listObjectsOfProperty(current, deptrans);
     words.add(model.getIndividual(current.getURI()));
     while (nt.hasNext()) {
     Individual word = model.getIndividual(nt.nextNode().asResource().getURI());
     NodeIterator nt2 = word.listPropertyValues(oliaCategory);
     boolean allow = true;
     while (nt2.hasNext()) {
     String cat = nt2.nextNode().asResource().getURI();
     if (forbidden.contains(cat)) {
     allow = false;
     }
     }
     if (allow) {
     words.add(word);
     }
     }

     StringBuilder sb = new StringBuilder();
     for (Individual word : words) {
     sb.append(word.getPropertyValue(lemma).asLiteral().getString());
     sb.append(" ");
     }
     current.addProperty(lexo, sb.toString().trim());
     System.out.println(current+"  - > "+sb.toString().trim());

     }

     // add properties for each word
     ExtendedIterator<Individual> itw = model.listIndividuals(NIFOntClasses.Word.getOntClass(model));
     for (; itw.hasNext(); ) {
     Individual current = itw.next();
     if (!current.hasProperty(lexo)) {
     current.addProperty(lexo, current.getPropertyValue(lemma).asLiteral().getString());
     }
     }

     System.exit(0);

     QueryExecution qe = QueryExecutionFactory.create("SELECT ?a ?b ?c {?s <" + oliaCategory.getURI() + "> ?o }", model);
     ResultSet rs = qe.execSelect();

     for (; rs.hasNext(); ) {
     QuerySolution qs = rs.next();
     Resource s = qs.getResource("s");
     Resource o = qs.getResource("o");
     System.out.println(s + "  " + o);

     }
     System.exit(0);


     QueryExecution qe2 = QueryExecutionFactory.create("SELECT ?s ?o {?s <" + oliaCategory.getURI() + "> ?o }", model);
     ResultSet rs2 = qe.execSelect();

     for (; rs2.hasNext(); ) {
     QuerySolution qs = rs2.next();
     Resource s = qs.getResource("s");
     Resource o = qs.getResource("o");
     System.out.println(s + "  " + o);

     }
     System.exit(0);            **/

}

