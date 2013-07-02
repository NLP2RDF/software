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
import java.util.Scanner;

/**
 * User: hellmann
 * Date: 01.07.13
 */
public class SPARQLValidator {
    private static Logger log = LoggerFactory.getLogger(SPARQLValidator.class);

    public static Model validate(OntModel toBeValidated, String query, String logPrefix) {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        NIFNamespaces.addRLOGPrefix(model);
        model.setNsPrefix("mylog", logPrefix);
        QueryExecution qe = QueryExecutionFactory.create(query, toBeValidated);
        ResultSet rs = qe.execSelect();

        for (; rs.hasNext(); ) {
            QuerySolution qs = rs.next();
            Resource relatedResource = qs.getResource("s");
            Literal message = qs.getLiteral("message");
            model.add(RLOGSLF4JBinding.log(logPrefix, message.getString(), RLOGIndividuals.ERROR, relatedResource.getURI(), log));

        }
        return model;
    }

    public static void main(String[] args) {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        String theString = convertStreamToString(SPARQLValidator.class.getClassLoader()
                .getResourceAsStream("sparql/hasReferenceContext.sparql")
        );
        model.read(SPARQLValidator.class.getClassLoader().getResourceAsStream("erroneous/nif-error-1.ttl"), "", "Turtle");

        RDFWriter rw = new JenaReadersWriters.RDFWriterRIOT_Turtle();
        rw.write(validate(model, theString, "http://example.org/ex#"), System.out, "");
        //QueryExecution qe = QueryExecutionFactory.create(theString, ModelFactory.createDefaultModel());

        //System.out.println(qe.execConstruct());


        //Query q = QueryFactory.create(theString);

        //System.out.println(theString);

        //SSE.parse()
        //Op op = SSE.readOp("anchorOfShouldMatchisStringSubstr.sparql");

    }

    public static String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


}
