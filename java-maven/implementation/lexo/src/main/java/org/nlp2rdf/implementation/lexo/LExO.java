/******************************************************************************/
/*  Copyright (C) 2010-2011, Sebastian Hellmann                               */
/*                                                                            */
/*  Licensed under the Apache License, Version 2.0 (the "License");           */
/*  you may not use this file except in compliance with the License.          */
/*  You may obtain a copy of the License at                                   */
/*                                                                            */
/*      http://www.apache.org/licenses/LICENSE-2.0                            */
/*                                                                            */
/*  Unless required by applicable law or agreed to in writing, software       */
/*  distributed under the License is distributed on an "AS IS" BASIS,         */
/*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  */
/*  See the License for the specific language governing permissions and       */
/*  limitations under the License.                                            */
/******************************************************************************/

package org.nlp2rdf.implementation.lexo;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.datatypes.xsd.XSDDateTime;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.vocab.*;
import org.nlp2rdf.implementation.stanfordcorenlp.StanfordWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.text.NumberFormat;
import java.util.*;


/**
 * Debug with  echo -n "This is a sentence." | mvn compile exec:java -e  -Dexec.mainClass="org.nlp2rdf.implementation.stanfordcore.StanfordCoreCLI" -Dexec.args="-f text -i -" | less
 *
 * @author Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 */

public class LExO {
    private static Logger log = LoggerFactory.getLogger(LExO.class);
    private static StanfordWrapper stanfordWrapper = new StanfordWrapper();

    private static OntModel nifModel = null;
    private static OntModel lexoModel = null;
    private static OntModel rlogModel = null;
    private static Map<String, String> queries = null;

    private static final String sparqlPrefix = "PREFIX lexo: <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/lexo#> \n" +
            "PREFIX stanford: <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/dep/stanford#> \n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
            "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
            "PREFIX olia: <http://purl.org/olia/olia.owl#> \n" +
            "PREFIX nif: <http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#> \n";

    private static String lexotypens = "http://example.org/type#";

    {
        init();

    }

    private int axiomCreationCount = 0;

    private void init() {

        String nif_core_owl = "org/uni-leipzig/persistence/nlp2rdf/ontologies/nif-core/nif-core.owl";
        String nif_core_inf_owl = "org/uni-leipzig/persistence/nlp2rdf/ontologies/nif-core/nif-core-inf.owl";
        InputStream is1 = LExO.class.getClassLoader().getResourceAsStream(nif_core_owl);
        InputStream is2 = LExO.class.getClassLoader().getResourceAsStream(nif_core_inf_owl);
        nifModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        nifModel.createAnnotationProperty(DC.description.getURI());
        nifModel.read(is1, "", "RDF/XML");
        //nifModel.read(is2, "", "RDF/XML");

        lexoModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        //InputStream aa = LExO.class.getClassLoader().getResourceAsStream("org/uni-leipzig/persistence/nlp2rdf/ontologies/vm/lexo/lexo.ttl");
        InputStream aa = LExO.class.getClassLoader().getResourceAsStream("lexo.ttl");
        lexoModel.read(aa, "", "N3");

        rlogModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        aa = LExO.class.getClassLoader().getResourceAsStream("org/uni-leipzig/persistence/nlp2rdf/ontologies/rlog/rlog.ttl");
        rlogModel.read(aa, "", "N3");


        queries = new HashMap<>();
        ExtendedIterator<Individual> eit = lexoModel.listIndividuals(LExOOntClasses.GenRule.getOntClass(lexoModel));
        Individual current = null;
        while (eit.hasNext()) {
            current = eit.next();
            String query = sparqlPrefix + current.getPropertyValue(LExODatatypeProperties.construct.getDatatypeProperty(lexoModel)).toString();
            try {
                QueryFactory.create(query);
            } catch (Exception qe) {
                System.out.println(current);
                System.out.println(query);
                qe.printStackTrace();
                System.exit(0);
            }
            queries.put(current.getURI(), query);
        }
        System.err.println("Parsing of " + queries.size() + " queries was successful");
    }


    public void processText(Individual context, OntModel inputModel, OntModel outputModel, NIFParameters nifParameters) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        nf.setMinimumFractionDigits(2);

        //prepare the outputModel
        outputModel.addSubModel(rlogModel);
        outputModel.addSubModel(lexoModel);

        //the logging event
        Resource logRes = outputModel.createResource(nifParameters.getLogPrefix() + UUID.randomUUID());
        logRes.addProperty(RDF.type, outputModel.createResource(RLOGOntClasses.Entry.getUri()));
        logRes.addProperty(RLOGObjectProperties.level.getObjectProperty(outputModel), outputModel.createResource(RLOGIndividuals.INFO.getUri()));
        XSDDateTime date = new XSDDateTime(Calendar.getInstance());
        logRes.addProperty(RLOGDatatypeProperties.date.getDatatypeProperty(outputModel), date.toString(), date.getNarrowedDatatype());
        StringBuilder logmessage = new StringBuilder();

        /*
        * Stanford
        * */
        Monitor stanford = MonitorFactory.getTimeMonitor("stanford").start();
        inputModel.addSubModel(nifModel);
        stanfordWrapper.process(context, inputModel, inputModel, nifParameters);
        logmessage.append("Total stanford time: ").append(nf.format(stanford.stop().getLastValue())).append("\n");

        /*
       *  Rule processing
       *  stored in model
       * */
        System.err.println("Starting execution of rules");
        Monitor querytimetotal = MonitorFactory.getTimeMonitor("querytimetotal").start();
        for (Object key : queries.keySet()) {

            Monitor mon = MonitorFactory.getTimeMonitor((String) key).start();
            String query = queries.get(key);
            QueryExecution qe = QueryExecutionFactory.create(query, inputModel);
            qe.execConstruct(outputModel);
            logRes.addProperty(outputModel.createProperty((String) key + "_time"), outputModel.createTypedLiteral(mon.stop().getLastValue(), XSDDatatype.XSDdouble));
            System.err.println(key + " needed: " + nf.format(mon.getLastValue()));
        }
        logmessage.append("Total rule time: ").append(nf.format(querytimetotal.stop().getLastValue())).append("\n");


        /*
       * Calculate coverage among nodes
       * */

        /*
       * get all available nodes
       * */
        Set<Resource> uncoveredNodes = new HashSet<>();
        String avnq = sparqlPrefix + "SELECT ?s ?anchorOf  {" +
                "{ ?s nif:dependencyTrans [] ; nif:anchorOf ?anchorOf . }" +
                "UNION " +
                "{ [] nif:dependencyTrans ?s . ?s nif:anchorOf ?anchorOf . }}";
        QueryExecution getUncoveredNodes = QueryExecutionFactory.create(avnq, inputModel);
        ResultSet rsavng = getUncoveredNodes.execSelect();
        Map<Resource, String> anchorOf = new HashMap<>();
        while (rsavng.hasNext()) {
            QuerySolution qs = rsavng.next();
            Resource s = qs.getResource("s");
            String uncovanch = qs.getLiteral("anchorOf").toString();
            uncoveredNodes.add(s);
            anchorOf.put(s, uncovanch);
        }

        int totalNodes = uncoveredNodes.size();

        ResIterator rit = outputModel.listSubjects();
        while (rit.hasNext()) {
            uncoveredNodes.remove(rit.nextResource());
        }


        int covered = totalNodes - uncoveredNodes.size();

        //print all uncovered nodes for debugging
        if (!uncoveredNodes.isEmpty()) {
            System.err.println("Uncovered nodes found:");
            for (Resource r : uncoveredNodes) {
                System.err.println("- UNCOV: " + anchorOf.get(r) + " [" + r + "]");
                Resource un = outputModel.getResource(r.getURI());
                un.addProperty(LExODatatypeProperties.uncovered.getDatatypeProperty(outputModel), "uncovered");
            }
        }

        // print all "skipped" statements  once
        List<Statement> skipped = outputModel.listStatements(null, LExODatatypeProperties.skipped.getDatatypeProperty(outputModel), (String) null).toList();
        if (!skipped.isEmpty()) {
            System.err.println(skipped.size() + " skipped nodes found.");
            for (Statement s : skipped) {
                Resource r = s.getSubject();
                //System.err.println("- SKIP: " + anchorOf.get(r) + ", Reason: " + s.getObject().asLiteral().toString() + " [" + r + "]");
                outputModel.remove(s);
            }
        }


        /*******
         * merging of axioms
         ******/
        Map<String, Resource> nif2classUri = new HashMap<>();
        determine_name(outputModel, nif2classUri);
        boolean repeat = true;
        Set<String> finished = new HashSet<>();

        List<Resource> nifResources = outputModel.listSubjectsWithProperty(LExOObjectProperties.axDesc.getObjectProperty(outputModel)).toList();
        while (repeat) {
            repeat = false;
            for (Resource current : nifResources) {

                if (finished.contains(current.getURI())) {
                    continue;
                }
                repeat = repeat || build_axioms(current, outputModel, nif2classUri, finished);
            }
        }

        /*********
         * quality check:
         * any bnodes, that are nowhere object
         ********/

        String bnodesQuery = sparqlPrefix + "SELECT ?bn ?p ?o {" +
                " ?bn ?p ?o " +
                "FILTER (isBlank(?bn) ) " +
                "FILTER (NOT EXISTS { [] ?in ?bn } )" +
                "FILTER (NOT EXISTS { ?bn rdfs:subClassOf [] } )" +
                " }";
        QueryExecution bnodes = QueryExecutionFactory.create(bnodesQuery, outputModel);
        ResultSet bnodesrs = bnodes.execSelect();
        int bnodesrsSize = 0;
        while (bnodesrs.hasNext()) {
            QuerySolution qs = bnodesrs.next();
            //Resource s = qs.getResource("bn");
            System.err.println("unconnected blank nodes found: " + qs);
            bnodesrsSize++;
        }
        System.err.println(axiomCreationCount + " axioms created.");
        System.err.println(bnodesrsSize + " unconnected blank nodes found.");
        logRes.addProperty(RLOGDatatypeProperties.message.getDatatypeProperty(outputModel), outputModel.createLiteral(logmessage.toString()));
        System.err.println("Coverage: " + " " + covered + " of " + totalNodes + " (" + nf.format(100 * (double) covered / totalNodes) + "%)");
    }

    public boolean build_axioms(Resource currentNIFResource, OntModel model, Map<String, Resource> nif2classUri, Set<String> finished) {

        //split all axiomdescriptions of resource
        StmtIterator sit = currentNIFResource.listProperties(LExOObjectProperties.axDesc.getObjectProperty(model));
        Set<Resource> axioms = new HashSet<>();
        Set<Resource> axiomParts = new HashSet<>();
        Resource className = nif2classUri.get(currentNIFResource.getURI());
        while (sit.hasNext()) {
            Statement stmt = sit.nextStatement();
            Resource currentAxDescriptor = stmt.getObject().asResource();
            //this is null, in some cases.
            Resource axTarget = currentAxDescriptor.getPropertyResourceValue(LExOObjectProperties.axTarget.getObjectProperty(model));
            if (axTarget != null) {
                if (finished.contains(axTarget.getURI())) {
                    if (currentAxDescriptor.hasProperty(RDF.type, LExOOntClasses.Axiom.getOntClass(model))) {
                        axioms.add(currentAxDescriptor);
                    } else if (currentAxDescriptor.hasProperty(RDF.type, LExOOntClasses.AxiomPart.getOntClass(model))) {
                        axiomParts.add(currentAxDescriptor);
                    }
                } else {
                    return false;
                }
            }
        }

        Resource result = null;
        List<Resource> intersections = new ArrayList<Resource>();

        if (className != null) {
            intersections.add(className);
        }
        for (Resource part : axiomParts) {

            Resource realTarget = nif2classUri.get(part.getPropertyResourceValue(LExOObjectProperties.axTarget.getObjectProperty(model)).getURI());
            Resource axSemantic = part.getPropertyResourceValue(LExOAnnotationProperties.axSemantic.getAnnotationProperty(model));

            if (axSemantic.getURI().equals(OWL.someValuesFrom.getURI())) {
                ObjectProperty axProperty = model.createObjectProperty(part.getPropertyResourceValue(LExOAnnotationProperties.axProperty.getAnnotationProperty(model)).getURI());
                intersections.add(model.createSomeValuesFromRestriction(null, axProperty, realTarget));
            } else {
                intersections.add(realTarget);
            }
        }

        if (intersections.size() == 1) {
            result = intersections.get(0);
        } else if (intersections.isEmpty()) {
            //not sure
            System.err.println("isempty");
        } else {
            RDFList list = model.createList();
            for (Resource r : intersections) {
                list = list.with(r);
            }
            result = model.createIntersectionClass(null, list);
        }


        for (Resource axiom : axioms) {
            Resource realTarget = nif2classUri.get(axiom.getPropertyResourceValue(LExOObjectProperties.axTarget.getObjectProperty(model)).getURI());
            Resource axSemantic = axiom.getPropertyResourceValue(LExOAnnotationProperties.axSemantic.getAnnotationProperty(model));
            if (axSemantic.getURI().equals(OWL.equivalentClass.getURI())) {
                ((OntClass) result).addEquivalentClass(realTarget);
                axiomCreationCount++;
            } else if (axSemantic.getURI().equals(RDFS.subClassOf.getURI())) {
                ((OntClass) result).addSuperClass(realTarget);
                axiomCreationCount++;
            } else {
                System.out.println("Please implement: " + axSemantic);
                System.exit(0);
            }
        }
        nif2classUri.put(currentNIFResource.getURI(), result);
        finished.add(currentNIFResource.getURI());
        return true;
    }

    public void determine_name(OntModel model, Map<String, Resource> nif2classUri) {
        ResIterator rit = model.listSubjectsWithProperty(LExOObjectProperties.axDesc.getObjectProperty(model));
        List<Resource> bns = new ArrayList<>();
        while (rit.hasNext()) {
            Resource currentAxDescriptor = null;
            SortedSet<Sorter> sortedNames = new TreeSet<Sorter>();

            Resource currentResource = rit.nextResource();

            //get all axiomdescriptions of resource
            StmtIterator sit = currentResource.listProperties(LExOObjectProperties.axDesc.getObjectProperty(model));
            boolean hasOneClassPart = false;
            while (sit.hasNext()) {
                Statement stmt = sit.nextStatement();
                if (stmt.getObject().asResource().hasProperty(RDF.type, LExOOntClasses.ClassPart.getOntClass(model))) {
                    currentAxDescriptor = stmt.getObject().asResource();
                    bns.add(currentAxDescriptor);
                    sortedNames.add(new Sorter(currentAxDescriptor.getProperty(LExODatatypeProperties.cnOrder.getDatatypeProperty(model)).
                            getObject().asLiteral().getInt(), currentAxDescriptor.getProperty(LExODatatypeProperties.cnPart.getDatatypeProperty(model)).getObject().asLiteral().toString()));
                    hasOneClassPart = true;
                }
            }

            if (hasOneClassPart) {
                StringBuilder sb = new StringBuilder(lexotypens);
                for (Sorter s : sortedNames) {
                    sb.append(s.getName());
                    sb.append("_");
                }
                String classUri = sb.substring(0, sb.length() - 1);
                currentResource.addProperty(LExODatatypeProperties.className.getDatatypeProperty(model), model.createResource(classUri));
                nif2classUri.put(currentResource.getURI(), model.createClass(classUri));
            }
        }

        //delete?
        /*for (Resource r : bns) {
            r.removeProperties();
        } */
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

