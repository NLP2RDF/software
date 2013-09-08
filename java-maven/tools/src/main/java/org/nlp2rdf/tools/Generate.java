package org.nlp2rdf.tools;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * User: hellmann
 * Date: 06.05.13
 */
public class Generate {
    private static Logger log = LoggerFactory.getLogger(Generate.class);


    public static void main(String[] args) throws IOException {

        //, ComponentInitException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, LearningProblemUnsupportedException {

        //used for the outfile
        FileWriter outfile = null;
        Template template = null;
        //ontology to render
        OntModel model = ModelFactory.createOntologyModel();


        /*  first, get and initialize an engine  */
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        /*  create a context and add data */
        VelocityContext context = new VelocityContext();


        OptionParser parser = new OptionParser();
        parser.acceptsAll(asList("h", "?", "help"), "Show help.");
        parser.acceptsAll(asList("t", "template"), "One of the templates under src/main/java/resources, e.g. DatatypeProperties, ObjectProperties, OntClasses").withRequiredArg().ofType(String.class);
        parser.acceptsAll(asList("f", "outfile"), "The name of the file output should be written to").withRequiredArg().ofType(String.class);
        parser.acceptsAll(asList("o", "ontology"), "The name of the file output should be written to").withRequiredArg().ofType(String.class);

        // parse options and display a message for the user in case of problems
        OptionSet options = null;
        try {
            options = parser.parse(args);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + ". Use -? to get help.");
            System.exit(0);
        }
        if (options.has("?")) {
            parser.printHelpOn(System.out);
            String addHelp = "";
            System.out.println();
            System.out.println(addHelp);
            // main script
        } else {
            if (!options.hasArgument("t")) {
                System.out.println("Please specify a template  (using the -t option).");
                System.exit(0);
            } else {
                /*  next, get the Template  */
                template = ve.getTemplate((String) options.valueOf("t"));
            }

            if (!options.hasArgument("f") || !((String) options.valueOf("f")).endsWith(".java")) {
                System.out.println("Please specify an output file (ending on .java and using the -f option).");
                System.exit(0);
            } else {
                String className = (String) options.valueOf("f");
                outfile = new FileWriter((String) options.valueOf("f"));
                context.put("className", className.substring(0, className.length() - 5).substring(className.lastIndexOf('/') + 1));
            }

            if (!options.hasArgument("o")) {
                System.out.println("Please specify an ontology url  (using the -o option).");
                System.exit(0);
            } else {
                System.out.println("Trying to read ontology from " + options.valueOf("o"));
                model.read((String) options.valueOf("o"));
                context.put("ontology", (String) options.valueOf("o"));

            }

        }

        Ontology o = getOntologyObject(model.listOntologies());


        Statement preferredNamespacePrefix = o.getProperty(model.createProperty("http://purl.org/vocab/vann/preferredNamespacePrefix"));
        if (preferredNamespacePrefix != null && preferredNamespacePrefix.getObject().canAs(Literal.class)) {
            context.put("preferredNamespacePrefix", preferredNamespacePrefix.getObject().asLiteral().getString());
        }
        Statement preferredNamespaceUri = o.getProperty(model.createProperty("http://purl.org/vocab/vann/preferredNamespaceUri"));

        if (preferredNamespaceUri != null && preferredNamespaceUri.getObject().canAs(Literal.class)) {
            context.put("preferredNamespaceUri", preferredNamespaceUri.getObject().asLiteral().getString());
        }
        context.put("datatypeProperties", doit(model.listDatatypeProperties(), context));
        context.put("objectProperties", doit(model.listObjectProperties(), context));
        context.put("ontClasses", doit(model.listClasses(), context));
        context.put("individuals", doit(model.listIndividuals(), context));
        context.put("annotationProperties", doit(model.listAnnotationProperties(), context));
        //StringWriter st = new StringWriter();
        //template.merge(context, st);
        //System.out.println(st.toString());

        template.merge(context, outfile);
        outfile.flush();
        outfile.close();
    }

    public static Ontology getOntologyObject(ExtendedIterator<Ontology> ontologies) {
        Ontology o = null;
        if (ontologies.hasNext()) {
            o = ontologies.next();
        }

        if (ontologies.hasNext()) {
            log.warn("More than one ontology object was found.");
        }
        return o;
    }


    public static ArrayList doit(ExtendedIterator<? extends OntResource> it, VelocityContext context) {
        /* create our list of maps  */
        ArrayList list = new ArrayList();
        for (OntResource r = null; it.hasNext(); ) {
            r = it.next();
            if (r.isAnon()) {
                continue;
            }
            Map map = new HashMap();
            map.put("name", r.getLocalName());
            map.put("label", label(r));
            map.put("comment", comment(r));
            list.add(map);

        }
        return list;


    }

    private static String label(OntResource r) {
        String label = r.getLabel("en");
        label = (label == null || label.isEmpty()) ? r.getLabel(null) : label;
        label = (label == null || label.isEmpty()) ? "TODO add label" : label;
        return label;
    }

    private static String comment(OntResource r) {
        String comment = r.getComment("en");
        comment = (comment == null || comment.isEmpty()) ? r.getComment(null) : comment;
        comment = (comment == null) || comment.isEmpty() ? "TODO add comment" : comment;
        return comment;
    }
}
