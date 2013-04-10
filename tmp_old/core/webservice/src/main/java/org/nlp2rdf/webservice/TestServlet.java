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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class TestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        handle(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        handle(httpServletRequest, httpServletResponse);
    }

    private void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/plain");
        PrintWriter out = httpServletResponse.getWriter();
        out.println("Request URL: " + httpServletRequest.getRequestURL().toString());
        out.println(NIFServlet.printParameterMap(httpServletRequest));
        out.close();
    }


}
