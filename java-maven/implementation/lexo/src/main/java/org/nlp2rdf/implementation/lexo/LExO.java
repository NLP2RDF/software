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

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.urischemes.URIScheme;
import org.nlp2rdf.core.vocab.NIFDatatypeProperties;
import org.nlp2rdf.implementation.stanfordcorenlp.StanfordWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


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


    public void processText(String prefix, Individual context, URIScheme urischeme, OntModel model, NIFParameters nifParameters) {
        String contextString = context.getPropertyValue(NIFDatatypeProperties.isString.getDatatypeProperty(model)).asLiteral().getString();
        stanfordWrapper.processText(prefix, context, urischeme, model, nifParameters);

        String nif_core_owl = "org/uni-leipzig/persistence/nlp2rdf/nif-core/nif-core.owl";
        String nif_core_inf_owl = "org/uni-leipzig/persistence/nlp2rdf/nif-core/nif-core-inf.owl";
        InputStream is1 = LExO.class.getClassLoader().getResourceAsStream(nif_core_owl);
        InputStream is2 = LExO.class.getClassLoader().getResourceAsStream(nif_core_inf_owl);
        OntModel model1 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        OntModel model2 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        model1.read(is1, "", "RDF/XML");
        model2.read(is2, "", "RDF/XML");

        model.addSubModel(model1);
        model.addSubModel(model2);

        model.prepare();

        OntModel suite = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        InputStream aa = LExO.class.getClassLoader().getResourceAsStream("preprocess.ttl");
        suite.read(aa, "", "N3");

        String lexp = "http://example.org/lexo#";
        String cprefix = "PREFIX lexo: <http://example.org/lexo#> \n" +
                "PREFIX s: <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/dep/stanford#> \n" +
                "PREFIX olia: <http://purl.org/olia/olia.owl#> \n" +
                "PREFIX nif: <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#> \n";
        ExtendedIterator<Individual> eit = suite.listIndividuals(suite.createClass(lexp + "Preprocessing"));
        Individual current = null;
        OntModel intermediate = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        for (; eit.hasNext(); ) {
            current = eit.next();
            String query = current.getPropertyValue(suite.createDatatypeProperty(lexp + "query")).toString();
            QueryExecution qe = QueryExecutionFactory.create(cprefix + query, model);
            qe.execConstruct(intermediate);

        }


        String statquery = "SELECT ?res ?anchor  {?s nif:dependencyTrans ?res  . ?res nif:anchorOf ?anchor . FILTER (NOT EXISTS {[] s:det ?res }) }";
        QueryExecution qe = QueryExecutionFactory.create(cprefix + statquery, model);
        ResultSet rs = qe.execSelect();
        Set<Resource> res1 = new HashSet<>();
        Map<Resource, String> anchorOf = new HashMap<>();

        for (; rs.hasNext(); ) {
            QuerySolution qs = rs.next();
            Resource s = qs.getResource("res");
            res1.add(s);
            anchorOf.put(s, qs.getLiteral("anchor").toString());
        }

        int totalIDs = res1.size();

        String statquery2 = "SELECT ?s  {?s ?p ?o. FILTER (!isBlank(?s))  } ";//  FILTER (NOT EXISTS {?s lexo:handled \"true\" }) }";
        QueryExecution qe2 = QueryExecutionFactory.create(cprefix + statquery2, intermediate);
        ResultSet rs2 = qe2.execSelect();
        Set<Resource> res2 = new HashSet<>();
        for (; rs2.hasNext(); ) {
            QuerySolution qs = rs2.next();
            Resource s = qs.getResource("s");
            res1.remove(s);
        }

        int covered = totalIDs - res1.size();

        intermediate.write(System.out, "N3");

        for (Resource r : res1) {
            System.err.print(anchorOf.get(r));
            System.err.println(" [" + r + "]");
        }


        System.err.println("Coverage: " +  " " + res1.size() + " of " + totalIDs + " left: "+((double) covered / totalIDs)  );

        /** StmtIterator it1 = model.listStatements(null, NIFObjectProperties.dependencyTrans.getObjectProperty(model), (RDFNode) null);

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
        System.exit(0);
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

}

