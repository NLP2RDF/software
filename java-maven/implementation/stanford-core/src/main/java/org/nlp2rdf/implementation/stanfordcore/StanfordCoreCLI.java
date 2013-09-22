package org.nlp2rdf.implementation.stanfordcore;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
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

import java.io.IOException;

/**
 * User: hellmann
 * Date: 08.09.13
 */
public class StanfordCoreCLI {

    public static void main(String[] args) throws IOException {
        OptionParser parser = ParameterParser.getParser(args, "http://cli.nlp2rdf.org/stanfordcore#");
        // TODO as a courtesy to windows users
        // parser.acceptsAll(asList("outfile"), "a NIF RDF file with the result of validation as RDF, only takes effect, if outformat is 'turtle' or 'rdfxml'").withRequiredArg().ofType(File.class).describedAs("RDF file");
        ParameterParser.addOutFileParameter(parser);


        try {
            OptionSet options = ParameterParser.getOption(parser, args);

            // print help screen
            if (options.has("h")) {
                String addHelp = "";
                throw new ParameterException(addHelp);
            }

            //check whether web service and start, if necessary

            if (options.hasArgument("start")) {
                int portNumber = (Integer) options.valueOf("port");
                NIFParameters nifParameters = ParameterParser.parseOptions(options, true);
                System.err.println("Starting Web service at port " + portNumber);
                System.err.println("web service not implemented yet");
                System.exit(0);

            }

            NIFParameters nifParameters = ParameterParser.parseOptions(options, false);
            OntModel model = nifParameters.getInputModel();


            StanfordCoreWrapper s = new StanfordCoreWrapper();

            //some stats
            Monitor mon = MonitorFactory.getTimeMonitor(s.getClass().getCanonicalName()).start();

            int x = 0;
            for (ExtendedIterator<Individual> it = model.listIndividuals(NIFOntClasses.Context.getOntClass(model)); it.hasNext(); ) {

                Individual context = it.next();
                s.processText(nifParameters.getPrefix(), context, nifParameters.getUriScheme(), model, nifParameters);

                x++;
            }
            String finalMessage = "Annotated " + x + " nif:Context(s)  in " + mon.stop().getTotal() + " ms.  (avg.:" + (mon.getAvg()) + ") producing " + model.size() + " triples";
            model.add(RLOGSLF4JBinding.log(nifParameters.getLogPrefix(), finalMessage, RLOGIndividuals.DEBUG, s.getClass().getCanonicalName(), null, null));
            model.setNsPrefix("dc","http://purl.org/dc/elements/1.1/");

            NIFNamespaces.addNifPrefix(model);
            model.setNsPrefix("olia", "http://purl.org/olia/olia.owl#");
            model.setNsPrefix("prefix", nifParameters.getPrefix());
            model.write(System.out, Format.toJena(nifParameters.getOutputFormat()));
        } catch (ParameterException e) {
            ParameterParser.die(parser, e.getMessage());
            // main script
        }

    }
}
