package org.nlp2rdf.vocabularymodule.olia.models;

import org.apache.commons.collections.map.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;

public class Tibet { 

	public static MultiValueMap links = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		links.put("http://purl.org/olia/tibet.owl#CONJ", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/tibet.owl#QoM", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/tibet.owl#DEMnear.ADJ", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/tibet.owl#DEMnear.ADJ", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/tibet.owl#DEMnear.ADJ", "http://purl.org/olia/olia.owl#AttributivePronoun");
		links.put("http://purl.org/olia/tibet.owl#DEMnear.ADJ", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#DEMnear.ADJ", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#SEQ", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#SEQ", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#SEQ", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/tibet.owl#V_encodingerror2", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/tibet.owl#V_encodingerror2", "http://purl.org/olia/olia.owl#VerbalNoun");
		links.put("http://purl.org/olia/tibet.owl#V_encodingerror2", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/tibet.owl#V_encodingerror2", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/tibet.owl#V_encodingerror2", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/tibet.owl#QADJ-proNOM", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/tibet.owl#QADJ-proNOM", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/tibet.owl#QADJ-proNOM", "http://purl.org/olia/olia.owl#AttributivePronoun");
		links.put("http://purl.org/olia/tibet.owl#QADJ-proNOM", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/tibet.owl#QADJ-proNOM", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#QADJ-proNOM", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#VNst", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/tibet.owl#VNst", "http://purl.org/olia/olia.owl#VerbalNoun");
		links.put("http://purl.org/olia/tibet.owl#VNst", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/tibet.owl#VNst", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/tibet.owl#VNst", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/tibet.owl#NAME", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/tibet.owl#NAME", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/tibet.owl#TOP", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/tibet.owl#DEMfar.ADJ", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#DEMfar.ADJ", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/tibet.owl#DEMfar.ADJ", "http://purl.org/olia/olia.owl#AttributivePronoun");
		links.put("http://purl.org/olia/tibet.owl#DEMfar.ADJ", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#DEMfar.ADJ", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/tibet.owl#PRON", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#PRON", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#PRON", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/tibet.owl#PRON", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/tibet.owl#RADJ", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/tibet.owl#RADJ-proNOM", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/tibet.owl#RADJ-proNOM", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#RADJ-proNOM", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#RADJ-proNOM", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/tibet.owl#RADJ-proNOM", "http://purl.org/olia/olia.owl#AttributivePronoun");
		links.put("http://purl.org/olia/tibet.owl#QoADV", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/tibet.owl#VN", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/tibet.owl#VN", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/tibet.owl#VN", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/tibet.owl#VN", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/tibet.owl#VN", "http://purl.org/olia/olia.owl#VerbalNoun");
		links.put("http://purl.org/olia/tibet.owl#INTJ", "http://purl.org/olia/olia.owl#Interjection");
		links.put("http://purl.org/olia/tibet.owl#NUM", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/tibet.owl#NUM", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/tibet.owl#NUM", "http://purl.org/olia/olia.owl#CardinalNumber");
		links.put("http://purl.org/olia/tibet.owl#DEMfar", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#DEMfar", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#ONM", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/tibet.owl#QoI", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/tibet.owl#SUM", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/tibet.owl#PRON1", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#PRON1", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/tibet.owl#PRON1", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/tibet.owl#PRON1", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#DEMnear", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#DEMnear", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#EPRON2-pl", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#EPRON2-pl", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#RFLX", "http://purl.org/olia/olia.owl#ReflexivePronoun");
		links.put("http://purl.org/olia/tibet.owl#RFLX", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#RFLX", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/tibet.owl#RFLX", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#IQ", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/tibet.owl#VFIN", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/tibet.owl#VFIN", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/tibet.owl#QPRON", "http://purl.org/olia/olia.owl#InterrogativePronoun");
		links.put("http://purl.org/olia/tibet.owl#QPRON", "http://purl.org/olia/olia.owl#WHPronoun");
		links.put("http://purl.org/olia/tibet.owl#QPRON", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#QPRON", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#excl", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#excl", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/tibet.owl#excl", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/tibet.owl#excl", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#ADJ", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/tibet.owl#PRON3", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/tibet.owl#PRON3", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#PRON3", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#PRON3", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/tibet.owl#EDEM", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#EDEM", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#proNOM", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#proNOM", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#QPRONrel", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#QPRONrel", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#QPRONrel", "http://purl.org/olia/olia.owl#WHPronoun");
		links.put("http://purl.org/olia/tibet.owl#QPRONrel", "http://purl.org/olia/olia.owl#RelativePronoun");
		links.put("http://purl.org/olia/tibet.owl#PRON2", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#PRON2", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#PRON2", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/tibet.owl#PRON2", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/tibet.owl#PRON2voc", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#PRON2voc", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#QADJ", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/tibet.owl#QADJ", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/tibet.owl#incl", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/tibet.owl#incl", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/tibet.owl#incl", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#incl", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#V", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/tibet.owl#V", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/tibet.owl#ADJ-proNOM", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/tibet.owl#ADJ-proNOM", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/tibet.owl#ADJ-proNOM", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/tibet.owl#ADJ-proNOM", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/tibet.owl#ADJ-proNOM", "http://purl.org/olia/olia.owl#AttributivePronoun");
	}
	
	public static MultiValueMap hasTag = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		hasTag.put("ADJ-proNOM", "http://purl.org/olia/tibet.owl#ADJ-proNOM");
		hasTag.put("AUX", "http://purl.org/olia/tibet.owl#AUX");
		hasTag.put("Abl", "http://purl.org/olia/tibet.owl#Abl");
		hasTag.put("Abs", "http://purl.org/olia/tibet.owl#Abs");
		hasTag.put("Aes", "http://purl.org/olia/tibet.owl#Aes");
		hasTag.put("Com", "http://purl.org/olia/tibet.owl#Com");
		hasTag.put("DM", "http://purl.org/olia/tibet.owl#DM");
		hasTag.put("DatLoc", "http://purl.org/olia/tibet.owl#DatLoc");
		hasTag.put("Erg", "http://purl.org/olia/tibet.owl#Erg");
		hasTag.put("FIN", "http://purl.org/olia/tibet.owl#FIN");
		hasTag.put("FM", "http://purl.org/olia/tibet.owl#FM");
		hasTag.put("Gen", "http://purl.org/olia/tibet.owl#Gen");
		hasTag.put("Instr", "http://purl.org/olia/tibet.owl#Instr");
		hasTag.put("Loc", "http://purl.org/olia/tibet.owl#Loc");
		hasTag.put("LocPurp", "http://purl.org/olia/tibet.owl#LocPurp");
		hasTag.put("MV", "http://purl.org/olia/tibet.owl#MV");
		hasTag.put("NEG", "http://purl.org/olia/tibet.owl#NEG");
		hasTag.put("PPosAbl", "http://purl.org/olia/tibet.owl#PPosAbl");
		hasTag.put("PPosCom", "http://purl.org/olia/tibet.owl#PPosCom");
		hasTag.put("PPosInstr", "http://purl.org/olia/tibet.owl#PPosInstr");
		hasTag.put("PPosLoc", "http://purl.org/olia/tibet.owl#PPosLoc");
		hasTag.put("QADJ-proNOM", "http://purl.org/olia/tibet.owl#QADJ-proNOM");
		hasTag.put("QF", "http://purl.org/olia/tibet.owl#QF");
		hasTag.put("RADJ-proNOM", "http://purl.org/olia/tibet.owl#RADJ-proNOM");
		hasTag.put("V", "http://purl.org/olia/tibet.owl#V");
		hasTag.put("VNst", "http://purl.org/olia/tibet.owl#VNst");
		hasTag.put("V\u00E2\u0081\u00BF", "http://purl.org/olia/tibet.owl#V_encodingerror2");
		hasTag.put("~Loc", "http://purl.org/olia/tibet.owl#_Loc");
		hasTag.put("chain.mod", "http://purl.org/olia/tibet.owl#chain.mod");
		hasTag.put("chain.purp", "http://purl.org/olia/tibet.owl#chain.purp");
		hasTag.put("chained", "http://purl.org/olia/tibet.owl#chained");
		hasTag.put("compound", "http://purl.org/olia/tibet.owl#compound");
		hasTag.put("cond", "http://purl.org/olia/tibet.owl#cond");
		hasTag.put("cond.intro", "http://purl.org/olia/tibet.owl#cond.intro");
		hasTag.put("cres", "http://purl.org/olia/tibet.owl#cres");
		hasTag.put("endchain", "http://purl.org/olia/tibet.owl#endchain");
		hasTag.put("endchain-spch.intro", "http://purl.org/olia/tibet.owl#endchain-spch.intro");
		hasTag.put("lwOpMV", "http://purl.org/olia/tibet.owl#lwOpMV");
		hasTag.put("morph", "http://purl.org/olia/tibet.owl#morph");
		hasTag.put("pQ", "http://purl.org/olia/tibet.owl#pQ");
		hasTag.put("phrs-spch.intro", "http://purl.org/olia/tibet.owl#phrs-spch.intro");
		hasTag.put("quest.alt1", "http://purl.org/olia/tibet.owl#quest.alt1");
		hasTag.put("quest.alt2", "http://purl.org/olia/tibet.owl#quest.alt2");
		hasTag.put("reduplication", "http://purl.org/olia/tibet.owl#reduplication");
		hasTag.put("rel", "http://purl.org/olia/tibet.owl#rel");
		hasTag.put("simple", "http://purl.org/olia/tibet.owl#simple");
		hasTag.put("simple-spch.intro", "http://purl.org/olia/tibet.owl#simple-spch.intro");
	}
	

	}
	
