# Java Maven implementation of NIF
For an overview of the NLP2RDF/NIF project see here: http://persistence.uni-leipzig.org/nlp2rdf/

## Implementations
We started with an implementation using Jena. We are aware that this is quite a large dependency. Feel free to copy and paste any code into your project and adapt it.
We would be happy, if you were to share your code for NIF implementations using other RDF libraries. 

## NIF Validator
An up-to-date validator is supplied at root level: validate.jar
Parameters are specified here: http://persistence.uni-leipzig.org/nlp2rdf/spec/2.0.html
### Use the Jar
'''
java -jar validate.jar -h
java -jar validate.jar -i core/jena/src/test/resources/nif-erroneous-model.ttl 
java -jar validate.jar -i core/jena/src/test/resources/nif-erroneous-model.ttl -o turtle 

'''

### Use directly with Maven
'''
cd core/jena
mvn compile exec:java -e  -Dexec.mainClass="org.nlp2rdf.cli.Validate" -Dexec.args="-i nif-erroneous-model.ttl -o turtle"
'''

### Build Validator Jar 
the jar appears in core/jena/target
'''
cd core/jena
mvn clean compile assembly:single
'''


## Java RDF Libraries

### Jena


## NLP tools

### Stanford Core NLP
