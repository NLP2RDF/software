package org.nlp2rdf.core;


import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.*;
import org.apache.jena.riot.adapters.JenaReadersWriters;
import org.nlp2rdf.core.vocab.RLOGIndividuals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * User: hellmann
 * Date: 01.07.13
 */
public class SPARQLValidator {
    private static Logger log = LoggerFactory.getLogger(SPARQLValidator.class);

    private String logPrefix = RLOGSLF4JBinding.defaultlogprefix;
    //no logging, e.g. on CLI
    private boolean quiet = false;

    public SPARQLValidator() {
    }

    public SPARQLValidator(String logPrefix) {
        this.logPrefix = logPrefix;
    }

    public Model validate(OntModel toBeValidated, String query) {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        NIFNamespaces.addRLOGPrefix(model);
        model.setNsPrefix("mylog", logPrefix);
        QueryExecution qe = QueryExecutionFactory.create(query, toBeValidated);
        ResultSet rs = qe.execSelect();

        for (; rs.hasNext(); ) {
            QuerySolution qs = rs.next();
            Resource relatedResource = qs.getResource("s");
            Literal message = qs.getLiteral("message");
            model.add(RLOGSLF4JBinding.log(logPrefix, message.getString(), RLOGIndividuals.ERROR, relatedResource.getURI(), (quiet)?null:log));

        }
        return model;
    }

    public OntModel validate(OntModel toBeValidated) {
        OntModel output = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        output.setNsPrefix("rlog", "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#");
        List<String> tests = new ArrayList<String>();
        tests.add(getQuery("sparqltest/misspelling_wrong_vocab/RFC5147StringMisspelling.sparql"));

        tests.add(getQuery("sparqltest/indices/anchorOfShouldMatchisStringSubstr.sparql"));
        tests.add(getQuery("sparqltest/indices/beginIndexOfContextShouldBeZero.sparql"));
        tests.add(getQuery("sparqltest/indices/isStringLength.sparql"));
        tests.add(getQuery("sparqltest/missing_property/contextHasIsString.sparql"));
        tests.add(getQuery("sparqltest/missing_property/hasReferenceContext.sparql"));
        tests.add(getQuery("sparqltest/missing_property/RFC5147StringHasBeginIndex.sparql"));
        tests.add(getQuery("sparqltest/missing_property/RFC5147StringHasEndIndex.sparql"));
        tests.add(getQuery("sparqltest/missing_type/contextTypedAsRFC5147String.sparql"));
        tests.add(getQuery("sparqltest/misspelling_wrong_vocab/RFC5147StringMisspelling.sparql"));
        tests.add(getQuery("sparqltest/ranges/beginEndIndexAreNonNegativeInteger.sparql"));
        for (String q : tests) {
            //System.out.println(q);
            output.add(validate(toBeValidated, q));
        }
        return output;
    }

    public static void main(String[] args) {
        OntModel tobevalidated = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        tobevalidated.read(SPARQLValidator.class.getClassLoader().getResourceAsStream("src/test/resource/erroneous/nif-erroneous-model.ttl"), "", "Turtle");
        OntModel output = new SPARQLValidator().validate(tobevalidated);

        RDFWriter rw = new JenaReadersWriters.RDFWriterRIOT_Turtle();
        rw.write(output, System.out, "");
        //QueryExecution qe = QueryExecutionFactory.create(theString, ModelFactory.createDefaultModel());

        //System.out.println(qe.execConstruct());


        //Query q = QueryFactory.create(theString);

        //System.out.println(theString);

        //SSE.parse()
        //Op op = SSE.readOp("anchorOfShouldMatchisStringSubstr.sparql");

    }

    public static String getQuery(String file) {
        return convertStreamToString(SPARQLValidator.class.getClassLoader().getResourceAsStream(file));
    }


    public static String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


    public String getLogPrefix() {
        return logPrefix;
    }

    public void setLogPrefix(String logPrefix) {
        this.logPrefix = logPrefix;
    }


    public boolean isQuiet() {
        return quiet;
    }

    public void setQuiet(boolean quiet) {
        this.quiet = quiet;
    }
}
