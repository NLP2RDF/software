package org.nlp2rdf.vocabularymodule.olia.models;

import org.apache.commons.collections.map.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;

public class SfbSixThreeTwo { 

	public static MultiValueMap links = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		links.put("http://purl.org/olia/sfb632.owl#VINTR", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/sfb632.owl#DET", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/sfb632.owl#DET", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/sfb632.owl#PRDNOM", "http://purl.org/olia/olia.owl#Predicate");
		links.put("http://purl.org/olia/sfb632.owl#PRDNOM", "http://purl.org/olia/olia.owl#NominalPredicate");
		links.put("http://purl.org/olia/sfb632.owl#POSS", "http://purl.org/olia/olia.owl#PossessorRole");
		links.put("http://purl.org/olia/sfb632.owl#P", "http://purl.org/olia/olia.owl#Adposition");
		links.put("http://purl.org/olia/sfb632.owl#VTR", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/sfb632.owl#VDITR", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/sfb632.owl#COM", "http://purl.org/olia/olia.owl#ComitativeRole");
		links.put("http://purl.org/olia/sfb632.owl#DO", "http://purl.org/olia/olia.owl#SyntacticObject");
		links.put("http://purl.org/olia/sfb632.owl#DO", "http://purl.org/olia/olia.owl#DirectObject");
		links.put("http://purl.org/olia/sfb632.owl#DO", "http://purl.org/olia/olia.owl#SyntacticArgument");
		links.put("http://purl.org/olia/sfb632.owl#INSTR", "http://purl.org/olia/olia.owl#InstrumentRole");
		links.put("http://purl.org/olia/sfb632.owl#PRONPOS", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/sfb632.owl#PRONPOS", "http://purl.org/olia/olia.owl#PossessivePronoun");
		links.put("http://purl.org/olia/sfb632.owl#PRONPOS", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/sfb632.owl#NPRP", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/sfb632.owl#NPRP", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/sfb632.owl#MAN", "http://purl.org/olia/olia.owl#MannerRole");
		links.put("http://purl.org/olia/sfb632.owl#OBJ", "http://purl.org/olia/olia.owl#SyntacticArgument");
		links.put("http://purl.org/olia/sfb632.owl#OBJ", "http://purl.org/olia/olia.owl#PrepositionalObject");
		links.put("http://purl.org/olia/sfb632.owl#OBJ", "http://purl.org/olia/olia.owl#SyntacticObject");
		links.put("http://purl.org/olia/sfb632.owl#COOR", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/sfb632.owl#COOR", "http://purl.org/olia/olia.owl#CoordinatingConjunction");
		links.put("http://purl.org/olia/sfb632.owl#PRONPRS_P", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/sfb632.owl#PRONPRS_P", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/sfb632.owl#PRONPRS_P", "http://purl.org/olia/olia.owl#PronominalAdverb");
		links.put("http://purl.org/olia/sfb632.owl#PRONPRS_P", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/sfb632.owl#ARG", "http://purl.org/olia/olia.owl#SyntacticArgument");
		links.put("http://purl.org/olia/sfb632.owl#MAIN", "http://purl.org/olia/olia.owl#Clause");
		links.put("http://purl.org/olia/sfb632.owl#MAIN", "http://purl.org/olia/olia.owl#MainClause");
		links.put("http://purl.org/olia/sfb632.owl#MAIN", "http://purl.org/olia/olia.owl#FiniteClause");
		links.put("http://purl.org/olia/sfb632.owl#V", "http://purl.org/olia/olia.owl#VerbalHead");
		links.put("http://purl.org/olia/sfb632.owl#V", "http://purl.org/olia/olia.owl#Head");
		links.put("http://purl.org/olia/sfb632.owl#THEME", "http://purl.org/olia/olia.owl#ThemeRole");
		links.put("http://purl.org/olia/sfb632.owl#VMOD", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/sfb632.owl#VMOD", "http://purl.org/olia/olia.owl#ModalVerb");
		links.put("http://purl.org/olia/sfb632.owl#VMOD", "http://purl.org/olia/olia.owl#AuxiliaryVerb");
		links.put("http://purl.org/olia/sfb632.owl#NP", "http://purl.org/olia/olia.owl#NounHeadedPhrase");
		links.put("http://purl.org/olia/sfb632.owl#NP", "http://purl.org/olia/olia.owl#NounPhrase");
		links.put("http://purl.org/olia/sfb632.owl#NP", "http://purl.org/olia/olia.owl#Phrase");
		links.put("http://purl.org/olia/sfb632.owl#ADJ", "http://purl.org/olia/olia.owl#SyntacticAdjunct");
		links.put("http://purl.org/olia/sfb632.owl#ADJ", "http://purl.org/olia/olia.owl#Modifier");
		links.put("http://purl.org/olia/sfb632.owl#VN", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/sfb632.owl#VN", "http://purl.org/olia/olia.owl#VerbalNoun");
		links.put("http://purl.org/olia/sfb632.owl#VN", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/sfb632.owl#VN", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/sfb632.owl#VN", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/sfb632.owl#AP", "http://purl.org/olia/olia.owl#AdjectivePhrase");
		links.put("http://purl.org/olia/sfb632.owl#AP", "http://purl.org/olia/olia.owl#Phrase");
		links.put("http://purl.org/olia/sfb632.owl#ADV", "http://purl.org/olia/olia.owl#FiniteClause");
		links.put("http://purl.org/olia/sfb632.owl#ADV", "http://purl.org/olia/olia.owl#SubordinateClause");
		links.put("http://purl.org/olia/sfb632.owl#ADV", "http://purl.org/olia/olia.owl#Clause");
		links.put("http://purl.org/olia/sfb632.owl#ADV", "http://purl.org/olia/olia.owl#AdverbialSubordinateClause");
		links.put("http://purl.org/olia/sfb632.owl#PRONQUANT", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/sfb632.owl#PRONQUANT", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/sfb632.owl#PRONQUANT", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/sfb632.owl#PRONDEM", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/sfb632.owl#PRONDEM", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/sfb632.owl#AG", "http://purl.org/olia/olia.owl#ActorMacroRole");
		links.put("http://purl.org/olia/sfb632.owl#AG", "http://purl.org/olia/olia.owl#AgentRole");
		links.put("http://purl.org/olia/sfb632.owl#S", "http://purl.org/olia/olia.owl#Clause");
		links.put("http://purl.org/olia/sfb632.owl#VCOP", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/sfb632.owl#_AT", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/sfb632.owl#_AT", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/sfb632.owl#_AT", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/sfb632.owl#_AT", "http://purl.org/olia/olia.owl#AttributivePronoun");
		links.put("http://purl.org/olia/sfb632.owl#SUBJ", "http://purl.org/olia/olia.owl#SyntacticArgument");
		links.put("http://purl.org/olia/sfb632.owl#SUBJ", "http://purl.org/olia/olia.owl#SyntacticSubject");
		links.put("http://purl.org/olia/sfb632.owl#NCOM", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/sfb632.owl#NCOM", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/sfb632.owl#GOAL", "http://purl.org/olia/olia.owl#GoalRole");
		links.put("http://purl.org/olia/sfb632.owl#SUBCOM", "http://purl.org/olia/olia.owl#SubordinatingConjunction");
		links.put("http://purl.org/olia/sfb632.owl#SUBCOM", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/sfb632.owl#CAUSE", "http://purl.org/olia/olia.owl#CauseRole");
		links.put("http://purl.org/olia/sfb632.owl#PTC", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/sfb632.owl#ADV_P", "http://purl.org/olia/olia.owl#PronominalAdverb");
		links.put("http://purl.org/olia/sfb632.owl#ADV_P", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/sfb632.owl#ADV_P", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/sfb632.owl#ADV_P", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/sfb632.owl#PRONPRS", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/sfb632.owl#PRONPRS", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/sfb632.owl#PRONPRS", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/sfb632.owl#PRONPRS", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/sfb632.owl#CLF", "http://purl.org/olia/olia.owl#Classifier");
		links.put("http://purl.org/olia/sfb632.owl#CLF", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/sfb632.owl#PRONDEM_P", "http://purl.org/olia/olia.owl#PronominalAdverb");
		links.put("http://purl.org/olia/sfb632.owl#PRONDEM_P", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/sfb632.owl#PRONDEM_P", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/sfb632.owl#PRONDEM_P", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/sfb632.owl#PRONRFL", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/sfb632.owl#PRONRFL", "http://purl.org/olia/olia.owl#ReflexivePronoun");
		links.put("http://purl.org/olia/sfb632.owl#PRONRFL", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/sfb632.owl#PRONRFL", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/sfb632.owl#A", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/sfb632.owl#ATTR", "http://purl.org/olia/olia.owl#Clause");
		links.put("http://purl.org/olia/sfb632.owl#ATTR", "http://purl.org/olia/olia.owl#SubordinateClause");
		links.put("http://purl.org/olia/sfb632.owl#ATTR", "http://purl.org/olia/olia.owl#FiniteClause");
		links.put("http://purl.org/olia/sfb632.owl#ATTR", "http://purl.org/olia/olia.owl#RelativeClause");
		links.put("http://purl.org/olia/sfb632.owl#PRONREL", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/sfb632.owl#PRONREL", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/sfb632.owl#TIME", "http://purl.org/olia/olia.owl#TimeRole");
		links.put("http://purl.org/olia/sfb632.owl#PRONINT", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/sfb632.owl#PRONINT", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/sfb632.owl#_SU", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/sfb632.owl#_SU", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/sfb632.owl#LOC", "http://purl.org/olia/olia.owl#LocationRole");
		links.put("http://purl.org/olia/sfb632.owl#IO", "http://purl.org/olia/olia.owl#IndirectObject");
		links.put("http://purl.org/olia/sfb632.owl#IO", "http://purl.org/olia/olia.owl#SyntacticArgument");
		links.put("http://purl.org/olia/sfb632.owl#IO", "http://purl.org/olia/olia.owl#SyntacticObject");
		links.put("http://purl.org/olia/sfb632.owl#VAUX", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/sfb632.owl#VAUX", "http://purl.org/olia/olia.owl#AuxiliaryVerb");
		links.put("http://purl.org/olia/sfb632.owl#VAUX", "http://purl.org/olia/olia.owl#StrictAuxiliaryVerb");
		links.put("http://purl.org/olia/sfb632.owl#EXPER", "http://purl.org/olia/olia.owl#UndergoerMacroRole");
		links.put("http://purl.org/olia/sfb632.owl#EXPER", "http://purl.org/olia/olia.owl#ExperiencerRole");
		links.put("http://purl.org/olia/sfb632.owl#PP", "http://purl.org/olia/olia.owl#Phrase");
		links.put("http://purl.org/olia/sfb632.owl#PP", "http://purl.org/olia/olia.owl#PrepositionalPhrase");
		links.put("http://purl.org/olia/sfb632.owl#PP", "http://purl.org/olia/olia.owl#NounHeadedPhrase");
		links.put("http://purl.org/olia/sfb632.owl#PRONEXPL", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/sfb632.owl#PRONEXPL", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
	}
	
	public static MultiValueMap hasTag = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

	}
	

	}
	
