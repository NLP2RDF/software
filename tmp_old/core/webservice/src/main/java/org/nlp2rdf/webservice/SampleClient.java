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
import com.hp.hpl.jena.shared.JenaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 */
public class SampleClient {

    private static Logger log = LoggerFactory.getLogger(SampleClient.class);
    public static String exampleText = "That's a lot of nuts! That'll be four bucks, baby! You want fries with that? ";


    public static void main(String[] args) throws Exception {
        String nifServiceUrl1 = "http://localhost:8080/webservice/NIFConverter";
        String nifServiceUrl2 = "http://localhost:8080/webservice/NIFStemmer";

        //convert the text 2 RDF
        OntModel m = convertToNIF(nifServiceUrl1, exampleText);
        log.info("Conversion finished. Retrieved " + m.size() + " triples ");

        //apply the stemmer
        m = postToNIFService(nifServiceUrl2, m);
        log.info("retrieved " + m.size() + " triples ");
    }

    public static OntModel convertToNIF(String nifServiceUrl, String text) throws Exception {
        Map<String, String> m = new HashMap<String, String>();
        m.put("input", text);
        m.put("type", "text");
        return postToNIFService(nifServiceUrl, m);

    }


    public static OntModel postToNIFService(String nifServiceUrl, OntModel model) throws Exception {
        Map<String, String> m = new HashMap<String, String>();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        RDFWriter writer = model.getWriter("RDF/XML");
        writer.write(model, baos, "");
        m.put("input", baos.toString());
        m.put("type", "nif-owl");
        return postToNIFService(nifServiceUrl, m);

    }

    public static OntModel postToNIFService(String nifServiceUrl, Map<String, String> parameterMap) throws Exception {
        StringBuffer data = new StringBuffer();
        boolean first = true;
        for (String key : parameterMap.keySet()) {
            String value = parameterMap.get(key);
            // Construct data
            if (first) {
                first = false;
            } else {
                data.append("&");
            }
            data.append(URLEncoder.encode(key, "UTF-8")).append("=").append(URLEncoder.encode(value, "UTF-8"));

        }
        // Send data
        URL url = new URL(nifServiceUrl);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);

        OntModel ret = null;
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        try {
            wr.write(data.toString());
            wr.flush();

            //buffer the stream as a String to get possible error messages
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();
            //make a new inputstream from the string
            ByteArrayInputStream bais = new ByteArrayInputStream(sb.toString().getBytes());

            ret = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
            try {
                // Get the response with jena
                ret.read(bais, "");
            } catch (JenaException e) {
                //if Jena can not parse the method
                String msg = "An error occured, while reading the nif model with Jena: \n" + sb.toString();
                msg = (msg.length() > 500) ? msg.substring(0, 500) : msg;
                log.error(msg, e);
            }
        } finally {
            wr.close();
        }
        return ret;

    }
}
