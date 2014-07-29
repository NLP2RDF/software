package org.nlp2rdf.implementation.opennlp;
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.nlp2rdf.core.NIFNamespaces;
import org.nlp2rdf.core.urischemes.URIScheme;
import org.nlp2rdf.core.urischemes.URISchemeHelper;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.nlp2rdf.implementation.opennlp.NIFPOSSampleStreamFactory.Parameters;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import opennlp.tools.cmdline.ArgumentParser;
import opennlp.tools.cmdline.CmdLineUtil;
import opennlp.tools.cmdline.StreamFactoryRegistry;
import opennlp.tools.cmdline.TerminateToolException;
import opennlp.tools.cmdline.ArgumentParser.ParameterDescription;
import opennlp.tools.formats.AbstractSampleStreamFactory;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.util.ObjectStream;

/**
 * <b>Note:</b> Do not use this class, internal use only!
 */
public class NIFNameSampleStreamFactory extends
    AbstractSampleStreamFactory<NameSample> {

	public static final String NIF_FORMAT = "nif";

	interface Parameters {
		@ParameterDescription(valueName = "i", description = "input")
		String getInput();
		
		@ParameterDescription(valueName = "informat", description = "input format, default text")
		String getInSyntax();

		@ParameterDescription(valueName = "outformat", description = "output format, default turtle")
		String getSyntax();
		
		@ParameterDescription(valueName = "intype", description = "type of input, file or direct.")
		String getIntype();
		
		@ParameterDescription(valueName = "urischeme", description = "urischeme")
		String getUrischeme();
		
		@ParameterDescription(valueName = "prefix", description = "URI prefix")
		String getPrefix();

	}

	public static void registerFactory() {
		StreamFactoryRegistry.registerFactory(NameSample.class, NIF_FORMAT,
		    new NIFPOSSampleStreamFactory(Parameters.class));
	}

	protected <P> NIFNameSampleStreamFactory(Class<P> params) {
		super(params);
	}

	public ObjectStream<NameSample> create(String[] args) {
		Parameters params = ArgumentParser.parse(args, Parameters.class);

		String outSyntax = params.getSyntax();
		if(outSyntax==null) 
			outSyntax = "TURTLE";
		
		String inSyntax = params.getInSyntax();
		if(inSyntax==null) 
			inSyntax = "TURTLE";
		
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
		
		model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
		NIFNamespaces.addNifPrefix(model);
		model.setNsPrefix("olia", "http://purl.org/olia/olia.owl#");
		model.setNsPrefix("p", params.getPrefix());
		
		URIScheme uriScheme = URISchemeHelper.getInstance("RFC5147String");
		if(params.getUrischeme()!=null)
			uriScheme = URISchemeHelper.getInstance(params.getUrischeme());
		
		String prefix = params.getPrefix();
		if(prefix==null)
			prefix = "http://cli.nlp2rdf.org/opennlp#";

		try {
			model.read(new InputStreamReader(
			    CmdLineUtil.openInFile(new File(params.getInput())), "UTF-8"), inSyntax);

			System.setOut(new PrintStream(System.out, true, "UTF-8"));
			Property sentenceProp = model
			    .createProperty(NIFOntClasses.Sentence.getUri());
			ObjectStream<Resource> res = new TypedRDFResourceStream(model,
					NIFOntClasses.Sentence.getOntClass(model));

			return new NIFNameSampleStream(res);
		} catch (UnsupportedEncodingException e) {
			// this shouldn't happen
			throw new TerminateToolException(-1, "UTF-8 encoding is not supported: "
			    + e.getMessage(), e);
		}
	}

}
