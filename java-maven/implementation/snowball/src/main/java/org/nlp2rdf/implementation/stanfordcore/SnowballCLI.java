package org.nlp2rdf.implementation.stanfordcore;

import java.io.IOException;

/**
 * User: hellmann
 * Date: 08.09.13
 */
public class SnowballCLI {
    public static void main(String[] args) throws IOException {
       /* OptionParser parser = CLIParameterParser.getParser(args, "http://cli.nlp2rdf.org/validate#");
        OptionSet options = CLIParameterParser.getOption(parser, args);
        NIFParameters nifParameters = CLIParameterParser.parseOptions(parser, options);
        String outformat = nifParameters.getOutputFormat();
        OntModel model = nifParameters.getInputModel();

        StanfordCoreWrapper s = new StanfordCoreWrapper();

        for (ExtendedIterator<Individual> it = model.listIndividuals(NIFOntClasses.Context.getOntClass(model)); it.hasNext(); ) {
            Individual context = it.next();
            s.processText(nifParameters.getPrefix(), context, nifParameters.getUriScheme(), model);
        }

        NIFNamespaces.addNifPrefix(model);
        model.setNsPrefix("olia","http://purl.org/olia/olia.owl#");
        model.setNsPrefix("prefix", nifParameters.getPrefix());
        model.write(System.out, Format.toJena(nifParameters.getOutputFormat()));
*/
    }
}
