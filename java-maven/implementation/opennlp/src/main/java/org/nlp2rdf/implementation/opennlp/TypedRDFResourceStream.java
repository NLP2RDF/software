package org.nlp2rdf.implementation.opennlp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.util.ObjectStream;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class TypedRDFResourceStream implements ObjectStream<Resource> {

	private OntModel rdfModel = null;
	private List<OntModel> models;
	private List<Resource> resources = new ArrayList<Resource>();
	private OntClass type;

	public TypedRDFResourceStream(OntModel model, OntClass type) {

		this.rdfModel = model;
		this.resources = this.rdfModel.listResourcesWithProperty(RDF.type, type)
		    .toList();
		this.type = type;

	}
	
	public TypedRDFResourceStream(List<OntModel> models, OntClass type) {

		this.models = models;
		this.type = type;
	}

	public Model getModel() {
		return rdfModel;
	}

	public void reset() throws IOException, UnsupportedOperationException {
		this.resources = this.rdfModel.listSubjects().toList();
	}

	public void close() throws IOException {
		this.rdfModel.close();
	}

	public Resource read() throws IOException {
		if (this.resources.size() > 0) {
			return this.resources.remove(0);
		} else if(models != null){
			if(models.size() > 0) {
				rdfModel = models.remove(0);
				this.resources = this.rdfModel.listResourcesWithProperty(RDF.type, type)
					    .toList();
				return this.resources.remove(0);
			} else {
				return null;
			}
		}
		return null;
	}
}
