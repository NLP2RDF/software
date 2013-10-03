#!/bin/sh

mvn clean compile

ONTO="OntClasses DatatypeProperties ObjectProperties AnnotationProperties"
for one in $ONTO
	do 
		mvn exec:java -e   -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t ${one} -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/NIF${one}.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#"
		exit
	done

exit

mvn exec:java -e   -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t OntClasses -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/NIFOntClasses.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t DatatypeProperties -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/NIFDatatypeProperties.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t ObjectProperties -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/NIFObjectProperties.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t AnnotationProperties -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/NIFAnnotationProperties.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#"


mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t OntClasses -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/RLOGOntClasses.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t DatatypeProperties -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/RLOGDatatypeProperties.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t ObjectProperties -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/RLOGObjectProperties.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t Individuals -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/RLOGIndividuals.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#"


mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t OntClasses -f ../implementation/lexo/src/main/java/org/nlp2rdf/implementation/lexo/LExOOntClasses.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/lexo#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t DatatypeProperties -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/RLOGDatatypeProperties.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t ObjectProperties -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/RLOGObjectProperties.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t Individuals -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/RLOGIndividuals.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#"
