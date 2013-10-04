package org.nlp2rdf.implementation.lexo;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.nlp2rdf.cli.ParameterException;
import org.nlp2rdf.cli.ParameterParser;
import org.nlp2rdf.core.Format;
import org.nlp2rdf.core.NIFNamespaces;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.RLOGSLF4JBinding;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.nlp2rdf.core.vocab.RLOGIndividuals;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.util.Arrays.asList;
import static java.util.Arrays.asList;

//import org.eclipse.jetty.server.Server;

/**
 * User: hellmann
 * Date: 08.09.13
 */
public class LexoCLI {

    public static void main(String[] args) throws IOException {
        Monitor total = MonitorFactory.getTimeMonitor("total");
        OptionParser parser = ParameterParser.getParser(args, "http://cli.nlp2rdf.org/lexo#");
        ParameterParser.addOutFileParameter(parser);


        try {
            OptionSet options = ParameterParser.getOption(parser, args);
            ParameterParser.handleHelpAndWS(options);
            NIFParameters nifParameters = ParameterParser.parseOptions(options, false);


            //customize
            //switch with a pelletmodel
            //OntModel model = ModelFactory.createOntologyModel(PelletReasonerFactory.THE_SPEC);
            //OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
            OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF, ModelFactory.createDefaultModel());

            model.add(nifParameters.getInputModel());
            model.createOntology(nifParameters.getPrefix());
            nifParameters.setInputModel(model);

            LExO s = new LExO();
            //some stats
            Monitor mon = MonitorFactory.getTimeMonitor(s.getClass().getCanonicalName()).start();

            int x = 0;

            for (Individual context : model.listIndividuals(NIFOntClasses.Context.getOntClass(model)).toList()) {

                model = s.processText(nifParameters.getPrefix(), context, nifParameters.getUriScheme(), model, nifParameters);

                x++;
            }


            String finalMessage = "Annotated " + x + " nif:Context(s)  in " + mon.stop().getTotal() + " ms.  (avg.:" + (mon.getAvg()) + ") producing " + model.size() + " triples";
            model.add(RLOGSLF4JBinding.log(nifParameters.getLogPrefix(), finalMessage, RLOGIndividuals.DEBUG, s.getClass().getCanonicalName(), null, null));
            model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");

            NIFNamespaces.addNifPrefix(model);
            NIFNamespaces.addRLOGPrefix(model);
            model.setNsPrefix("olia", "http://purl.org/olia/olia.owl#");
            model.setNsPrefix("p", nifParameters.getPrefix());

            if(options.has("outfile")){
                FileWriter fw =new FileWriter((File)options.valueOf("outfile"));
                model.write(fw, Format.toJena(nifParameters.getOutputFormat()));

            }else{
                model.write(System.out, Format.toJena(nifParameters.getOutputFormat()));

            }
            //           model.writeAll(System.out,"", Format.toJena(nifParameters.getOutputFormat()));
            System.err.println("needed total: " + total.getLastValue());
        } catch (ParameterException e) {
            ParameterParser.die(parser, e.getMessage());
            // main script
        }

    }
}
