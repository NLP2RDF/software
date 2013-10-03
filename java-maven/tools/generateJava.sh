#!/bin/sh

mvn clean compile

ONTO="OntClasses DatatypeProperties ObjectProperties AnnotationProperties"
for one in $ONTO
	do 
		mvn exec:java -e   -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t ${one} -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/NIF${one}.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#"

		mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t ${one} -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/RLOG${one}.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#"

		mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t ${one} -f ../implementation/lexo/src/main/java/org/nlp2rdf/core/vocab/LExO${one}.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/lexo#"
	done
