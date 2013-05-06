#!/bin/sh

mvn clean compile exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t OntClasses -f ../core/nif/src/main/java/org/nlp2rdf/core/NIFOntClasses.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#"
mvn clean compile exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t DatatypeProperties -f ../core/nif/src/main/java/org/nlp2rdf/core/NIFDatatypeProperties.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#"
mvn clean compile exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t ObjectProperties -f ../core/nif/src/main/java/org/nlp2rdf/core/NIFObjectProperties.java -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#"
