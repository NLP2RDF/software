#!/bin/sh

mvn clean compile
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.tools.Generate" -Dexec.args="-t json-ld-context -f nif-core-context.jsonld -o http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core# -o https://raw.githubusercontent.com/w3c/itsrdf/master/its-rdf.rdf" 
