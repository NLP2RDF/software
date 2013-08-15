package eu.lod2.nlp2rdf.schema.error;

import java.util.List;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.Individual;

/**
 * Interface http://nlp2rdf.lod2.eu/schema/error/Error
 */

public interface IError extends Individual, eu.lod2.nlp2rdf.schema.IThing {

	/**
	 * Domain property Fatal
	 * with uri http://nlp2rdf.lod2.eu/schema/error/fatal
	 */

	public boolean existsFatal();

	public boolean hasFatal(java.lang.Boolean booleanValue);

	public java.lang.Boolean getFatal();

	public void setFatal(java.lang.Boolean booleanValue);

	public void removeFatal();

	/**
	 * Domain property Source
	 * with uri http://nlp2rdf.lod2.eu/schema/error/source
	 */

	public boolean existsSource();

	public boolean hasSource(java.lang.String stringValue);

	public int countSource();

	public Iterator<java.lang.String> iterateSource();

	public List<java.lang.String> listSource();

	public void addSource(java.lang.String stringValue);

	public void addAllSource(List<java.lang.String> stringList);

	public void removeSource(java.lang.String stringValue);

	public void removeAllSource();

	/**
	 * Domain property Message
	 * with uri http://nlp2rdf.lod2.eu/schema/error/hasMessage
	 */

	public boolean existsMessage();

	public boolean hasMessage(java.lang.String stringValue);

	public int countMessage();

	public Iterator<java.lang.String> iterateMessage();

	public List<java.lang.String> listMessage();

	public void addMessage(java.lang.String stringValue);

	public void addAllMessage(List<java.lang.String> stringList);

	public void removeMessage(java.lang.String stringValue);

	public void removeAllMessage();

}