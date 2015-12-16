package org.nlp2rdf.webservice;

import com.hp.hpl.jena.ontology.OntModel;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import org.aksw.rdfunit.enums.TestCaseExecutionType;
import org.aksw.rdfunit.io.writer.*;
import org.nlp2rdf.core.NIFParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.InvalidParameterException;

/**
 * User: hellmann
 * Date: 20.09.13
 */
public abstract class NIFServlet extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(NIFServlet.class);
    private int counter = 0;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        handle(httpServletRequest, httpServletResponse);
    }
    
    @Override
    protected void doOptions(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        //The following are CORS headers
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        handle(httpServletRequest, httpServletResponse);
    }

    public abstract OntModel execute(NIFParameters nifParameters) throws Exception;

    /**
     * this method answers GET and POST requests, which are treated the same.
     * - Validates parameters
     * - does the work (execute)
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    private void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

       //The following are CORS headers
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");

        try {

            //Validate and normalize input
            Monitor mon = MonitorFactory.getTimeMonitor("NIFParameters.getInstance").start();
            String defaultPrefix = httpServletRequest.getRequestURL().toString() + "#";
            NIFParameters nifParameters = NIFParameterWebserviceFactory.getInstance(httpServletRequest, defaultPrefix);
            log.debug("NIFParameters Object created: " + logMonitor(mon.stop()));

            //execute the task
            mon = MonitorFactory.getTimeMonitor("NIFServlet.execute").start();

            OntModel out = execute(nifParameters);
            out.setNsPrefix("p", defaultPrefix);
            log.debug("NIF Component executed task: " + logMonitor(mon.stop()));
            //write the response
            write(httpServletResponse, out, nifParameters.getOutputFormat());
            log.info("output (" + nifParameters.getOutputFormat() + ", " + nifParameters.getOutputFormat() + ") written, triples from input: " + nifParameters.getInputModel().size() + ", added by component: " + out.size());
            writeJamonLog();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            writeError(e.getMessage(), httpServletResponse);
            /*String msg = e.getMessage() + printParameterMap(httpServletRequest);
            log.error(msg);
            eu.lod2.nlp2rdf.schema.error.Error fatalerror = ErrorHandling.createError(true, requestUrl, msg, model);
            fatalerror.addSource(requestUrl);
            if (nifParameters != null) {
                write(httpServletResponse, model, nifParameters.getFormat());
            } else {
                write(httpServletResponse, model, "rdfxml");
            } */

        } catch (Exception e) {
            e.printStackTrace();
            writeError(e.getMessage(), httpServletResponse);
            /*String msg = "An error occured: " + e.getMessage() + printParameterMap(httpServletRequest);
            log.error(msg, e);
            eu.lod2.nlp2rdf.schema.error.Error fatalerror = ErrorHandling.createError(true, requestUrl, msg, model);
            fatalerror.addSource(requestUrl);
            if (nifParameters != null) {
                write(httpServletResponse, model, nifParameters.getFormat());
            } else {
                write(httpServletResponse, model, "rdfxml");
            }*/
        }
    }

    protected static String logMonitor(Monitor m) {
        return "needed: " + m.getLastValue() + " ms. (" + m.getTotal() + " total)";
    }

    protected void write(HttpServletResponse httpServletResponse, OntModel out, String format) throws IOException {

        //this is the printer where the output has to be on
        OutputStream outputStream = httpServletResponse.getOutputStream();

        //Default writer (RDFUnit)
        RDFWriter outputWriter = null;
        String contentType = "";

        switch (format.toLowerCase()) {

            // treat them the same
            case "turtle":
                outputWriter = new RDFStreamWriter(outputStream, "TURTLE");
                contentType = "text/turtle";
                break;
            case "rdfxml":
                outputWriter = new RDFStreamWriter(outputStream, "RDF/XML");
                contentType = "application/rdf+xml";
                break;
            case "n3":
                outputWriter = new RDFStreamWriter(outputStream, "N3");
                contentType = "text/rdf+n3";
            case "ntriples":
                outputWriter = new RDFStreamWriter(outputStream, "NTRIPLES");
                contentType = "text/rdf+n3";
                break;
            case "html": {
                outputWriter = RDFWriterFactory.createHTMLWriter(TestCaseExecutionType.rlogTestCaseResult, outputStream);
                contentType = "text/html";
                break;
            }
            case "text": {
                contentType = "text";
                break;
            }
            default:
                outputStream.close();
                throw new InvalidParameterException("There is no " + format + " output implemented at the moment. Sorry!");
        }

        httpServletResponse.setContentType(contentType);
        httpServletResponse.setCharacterEncoding("UTF-8");

        /*out.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        out.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        out.setNsPrefix("owl", "http://www.w3.org/2002/07/owl#");


        out.setNsPrefix("sso", "http://nlp2rdf.lod2.eu/schema/sso/");
        out.setNsPrefix("str", "http://nlp2rdf.lod2.eu/schema/string/");
        out.setNsPrefix("topic", "http://nlp2rdf.lod2.eu/schema/topic/");
        out.setNsPrefix("error", "http://nlp2rdf.lod2.eu/schema/error/");


        out.setNsPrefix("olia", "http://purl.org/olia/olia.owl#");
        out.setNsPrefix("olia-top", "http://purl.org/olia/olia-top.owl#");
        out.setNsPrefix("olia_system", "http://purl.org/olia/system.owl#");

        out.setNsPrefix("penn", "http://purl.org/olia/penn.owl#");
        out.setNsPrefix("penn-syntax", "http://purl.org/olia/penn-syntax.owl#");
        out.setNsPrefix("stanford", "http://purl.org/olia/stanford.owl#");

        out.setNsPrefix("brown", "http://purl.org/olia/brown.owl#");
         */


        try {
            if (outputWriter != null)
                outputWriter.write(out);
            else { // ct -> text
                outputStream.write(outputStream.toString().getBytes());
            }
        } catch (RDFWriterException e) {
            System.err.println("Cannot write to output: " + e.getMessage());
            e.printStackTrace();
        }

        //RDFWriter writer = out.getWriter(jenaFormat);
        //writer.setProperty("showXmlDeclaration", "true");
        //writer.setProperty("showDoctypeDeclaration", "true");
        //writer.write(out, outputStream, "");
        outputStream.close();


    }

    protected void writeError(String error, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("text/plain");
        httpServletResponse.setCharacterEncoding("UTF-8");

        //this is the printer where the output has to be on
        PrintWriter pw = httpServletResponse.getWriter();
        pw.println(error);
        pw.close();

    }

    public static String printParameterMap(HttpServletRequest httpServletRequest) {

        log.error("printing map:\n" +
                httpServletRequest.getRequestURL() + "\n" +
                httpServletRequest.getContextPath() + "\n" +
                httpServletRequest + "\n" +
                "parameters: " + httpServletRequest.getParameterMap().keySet() + "\n" +
                "");
        StringBuffer buf = new StringBuffer();
        for (Object key : httpServletRequest.getParameterMap().keySet()) {
            buf.append("\nParameter: " + key + " Values: ");
            for (String s : httpServletRequest.getParameterValues((String) key)) {
                buf.append(((s.length() > 200) ? s.substring(0, 200) + "..." : s) + " ");
            }
        }
        return buf.toString();
    }

    public synchronized void writeJamonLog() {
        counter++;
        if (counter % 100 == 0) {
            try {
                // Create file
                FileWriter fstream = new FileWriter("log/jamonlog.html");
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(MonitorFactory.getReport());
                //Close the output stream
                out.close();
            } catch (Exception e) {//Catch exception if any
                //we don't care
                //System.err.println("Error: " + e.getMessage());
            }
        }
    }

}
