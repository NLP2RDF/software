package org.nlp2rdf.implementation.stanfordcorenlp;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.nlp2rdf.cli.ParameterException;
import org.nlp2rdf.cli.ParameterParser;
import org.nlp2rdf.core.*;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.nlp2rdf.core.vocab.RLOGIndividuals;

import java.io.IOException;

//import org.eclipse.jetty.server.Server;

/**
 * User: hellmann
 * Date: 08.09.13
 */
public class StanfordCLI {

    public static void main(String[] args) throws IOException {
        OptionParser parser = ParameterParser.getParser(args, "http://cli.nlp2rdf.org/stanfordcore#");
        // TODO as a courtesy to windows users
        // parser.acceptsAll(asList("outfile"), "a NIF RDF file with the result of validation as RDF, only takes effect, if outformat is 'turtle' or 'rdfxml'").withRequiredArg().ofType(File.class).describedAs("RDF file");
        ParameterParser.addOutFileParameter(parser);
        try {
            OptionSet options = ParameterParser.getOption(parser, args);
            ParameterParser.handleHelpAndWS(options, "");
            NIFParameters nifParameters = ParameterParser.parseOptions(options, false);

            //customize
            OntModel model = nifParameters.getInputModel();
            StanfordWrapper s = new StanfordWrapper();
            //some stats
            Monitor mon = MonitorFactory.getTimeMonitor(s.getClass().getCanonicalName()).start();
            int x = 0;
            for (ExtendedIterator<Individual> it = model.listIndividuals(NIFOntClasses.Context.getOntClass(model)); it.hasNext(); ) {
                Individual context = it.next();
                s.processText(context, model, model, nifParameters);
                x++;
            }
            String finalMessage = "Annotated " + x + " nif:Context(s)  in " + mon.stop().getTotal() + " ms.  (avg.:" + (mon.getAvg()) + ") producing " + model.size() + " triples";
            model.add(RLOGSLF4JBinding.log(nifParameters.getLogPrefix(), finalMessage, RLOGIndividuals.DEBUG, s.getClass().getCanonicalName(), null, null));
            model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
            NIFNamespaces.addNifPrefix(model);
            model.setNsPrefix("olia", "http://purl.org/olia/olia.owl#");
            model.setNsPrefix("p", nifParameters.getPrefix());
            model.write(System.out, Format.toJena(nifParameters.getOutputFormat()));
        } catch (ParameterException e) {
            ParameterParser.die(parser, e.getMessage());
            // main script
        }

    }
}
