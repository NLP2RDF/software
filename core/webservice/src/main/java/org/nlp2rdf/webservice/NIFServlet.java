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

package org.nlp2rdf.webservice;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import org.nlp2rdf.core.NIFNamespaces;
import org.nlp2rdf.core.RLOGSLF4JBinding;
import org.nlp2rdf.core.vocab.RLOGIndividuals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;


public abstract class NIFServlet extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(NIFServlet.class);
    private int counter = 0;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        handle(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        handle(httpServletRequest, httpServletResponse);
    }

    public abstract void execute(NIFParameters nifParameters, OntModel diff) throws Exception;

    /**
     * this method answers GET and POST requests, which are treated the same.
     * - Validates parameters
     * - does the work (execute)
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws ServletException
     * @throws java.io.IOException
     */
    private void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        //this is the model that will be filled in the execute method
        OntModel output = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        String requestUrl = httpServletRequest.getRequestURL().toString();
        NIFParameters nifParameters = null;
        try {

            /******************
             * Validate and normalize input
             ******************/

            Monitor mon = MonitorFactory.getTimeMonitor("NIFParameters.getInstance").start();
            nifParameters = NIFParameters.getInstance(httpServletRequest);
            log.debug("NIFParameters Object created: " + logMonitor(mon.stop()));

            /******************
             * execute the task
             ******************/
            mon = MonitorFactory.getTimeMonitor("NIFServlet.execute").start();
            execute(nifParameters, output);
            log.debug("NIF Component executed task: " + logMonitor(mon.stop()));


            //write the response
            write(httpServletResponse, output, nifParameters.getOutputFormat());
            //log.info("output (" + nifParameters.getOutputFormat() + ") written, triples from input: " + triplesFromInput + ", added by component: " + triplesFromComponent);
            log.info("output (" + nifParameters.getOutputFormat() + ") written, triples from input: " + nifParameters.getInputModel().size() + ", created by component: " + output.size());
            writeJamonLog();

        } catch (IllegalArgumentException e) {
            NIFNamespaces.addRLOGPrefix(output);
            String msg = e.getMessage() + printParameterMap(httpServletRequest);

            if (nifParameters != null) {
                output.add(RLOGSLF4JBinding.log(nifParameters.getLogPrefix(), msg, RLOGIndividuals.ERROR, log));
                write(httpServletResponse, output, nifParameters.getOutputFormat());
            } else {
                output.add(RLOGSLF4JBinding.log(msg, RLOGIndividuals.ERROR, log));
                write(httpServletResponse, output, "RDF/XML");
            }

        } catch (Exception e) {
            NIFNamespaces.addRLOGPrefix(output);
            String msg = "An error occured: " + e.getMessage() + printParameterMap(httpServletRequest);
            if (nifParameters != null) {
                output.add(RLOGSLF4JBinding.log(nifParameters.getLogPrefix(), msg, RLOGIndividuals.ERROR, log));
                write(httpServletResponse, output, nifParameters.getOutputFormat());
            } else {
                output.add(RLOGSLF4JBinding.log(msg, RLOGIndividuals.ERROR, log));
                write(httpServletResponse, output, "RDF/XML");
            }
        }
    }

    protected static String logMonitor(Monitor m) {
        return "needed: " + m.getLastValue() + " ms. (" + m.getTotal() + " total)";
    }

    protected void write(HttpServletResponse httpServletResponse, OntModel out, String format) throws IOException {
        if (format.equalsIgnoreCase("RDF/XML")) {
            write(httpServletResponse, out, "RDF/XML", "application/rdf+xml");
        } else if (format.equalsIgnoreCase("TURTLE")) {
            write(httpServletResponse, out, "TURTLE", "text/rdf+n3");
        } else if (format.equalsIgnoreCase("N3")) {
            write(httpServletResponse, out, "N3", "text/rdf+n3");
        } else if (format.equalsIgnoreCase("N-TRIPLE")) {
            write(httpServletResponse, out, "N-TRIPLE", "text/rdf+n3");
        } else if (format.equalsIgnoreCase("json")) {
            throw new InvalidParameterException("There is no JSON output implemented at the moment. Sorry!");
        }

    }

    protected void write(HttpServletResponse httpServletResponse, OntModel out, String jenaFormat, String contentType) throws IOException {
        httpServletResponse.setContentType(contentType);
        httpServletResponse.setCharacterEncoding("UTF-8");

        //there are some problems with dl-learner, if individuals are not typed correctly
        /*for (ExtendedIterator<Individual> it = out.listIndividuals(); it.hasNext(); ) {
            it.next().addOntClass(OWL.Thing);
        }
        for (ExtendedIterator<ObjectProperty> it = out.listObjectProperties(); it.hasNext(); ) {
            it.next().addRDFType(OWL.ObjectProperty);
        }
        for (ExtendedIterator<DatatypeProperty> it = out.listDatatypeProperties(); it.hasNext(); ) {
            it.next().addRDFType(OWL.DatatypeProperty);
        }
        for (ExtendedIterator<OntClass> it = out.listClasses(); it.hasNext(); ) {
            it.next().addRDFType(OWL.Class);
        } */

        out.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        out.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        out.setNsPrefix("owl", "http://www.w3.org/2002/07/owl#");

        NIFNamespaces.addNifPrefix(out);

        out.setNsPrefix("olia", "http://purl.org/olia/olia.owl#");
        out.setNsPrefix("olia-top", "http://purl.org/olia/olia-top.owl#");
        out.setNsPrefix("olia_system", "http://purl.org/olia/system.owl#");

        out.setNsPrefix("penn", "http://purl.org/olia/penn.owl#");
        out.setNsPrefix("penn-syntax", "http://purl.org/olia/penn-syntax.owl#");
        out.setNsPrefix("stanford", "http://purl.org/olia/stanford.owl#");

        out.setNsPrefix("brown", "http://purl.org/olia/brown.owl#");


        //this is the printer where the output has to be on
        PrintWriter pw = httpServletResponse.getWriter();
        RDFWriter writer = out.getWriter(jenaFormat);
        writer.setProperty("showXmlDeclaration", "true");
        //writer.setProperty("showDoctypeDeclaration", "true");
        writer.write(out, pw, "");
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
