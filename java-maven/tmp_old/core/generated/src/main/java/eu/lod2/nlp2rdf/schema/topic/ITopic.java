package eu.lod2.nlp2rdf.schema.topic;

import java.util.List;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.Individual;

/**
 * Interface http://nlp2rdf.lod2.eu/schema/topic/Topic
 */

public interface ITopic extends Individual, eu.lod2.nlp2rdf.schema.IThing {

	/**
	 * Domain property OccursIn
	 * with uri http://nlp2rdf.lod2.eu/schema/topic/occursIn
	 */

	public boolean existsOccursIn();

	public boolean hasOccursIn(eu.lod2.nlp2rdf.schema.str.IDocument documentValue);

	public int countOccursIn();

	public Iterator<eu.lod2.nlp2rdf.schema.str.Document> iterateOccursIn();

	public List<eu.lod2.nlp2rdf.schema.str.Document> listOccursIn();

	public void addOccursIn(eu.lod2.nlp2rdf.schema.str.IDocument documentValue);

	public void addAllOccursIn(List<? extends eu.lod2.nlp2rdf.schema.str.IDocument> documentList);

	public void removeOccursIn(eu.lod2.nlp2rdf.schema.str.IDocument documentValue);

	public void removeAllOccursIn();

	/**
	 * Domain property CharacterticLemma
	 * with uri http://nlp2rdf.lod2.eu/schema/topic/characteristicLemma
	 */

	public boolean existsCharacterticLemma();

	public boolean hasCharacterticLemma(java.lang.String stringValue);

	public int countCharacterticLemma();

	public Iterator<java.lang.String> iterateCharacterticLemma();

	public List<java.lang.String> listCharacterticLemma();

	public void addCharacterticLemma(java.lang.String stringValue);

	public void addAllCharacterticLemma(List<java.lang.String> stringList);

	public void removeCharacterticLemma(java.lang.String stringValue);

	public void removeAllCharacterticLemma();

}