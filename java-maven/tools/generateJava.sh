#!/bin/sh

mvn clean compile
mvn exec:java -e   -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t OntClasses -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/NIFOntClasses.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t DatatypeProperties -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/NIFDatatypeProperties.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t ObjectProperties -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/NIFObjectProperties.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t AnnotationProperties -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/NIFAnnotationProperties.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#"


mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t OntClasses -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/RLOGOntClasses.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t DatatypeProperties -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/RLOGDatatypeProperties.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t ObjectProperties -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/RLOGObjectProperties.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#"
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t Individuals -f ../core/jena/src/main/java/org/nlp2rdf/core/vocab/RLOGIndividuals.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#"
