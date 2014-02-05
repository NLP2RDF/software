
Software
==========================
This repository contains software and tools using and implementing NIF 2.0.
* The [NLP2RDF/NIF project page] (http://nlp2rdf.org) provides an overview.
* See here for an overview of resources: [License, Persistence, Versioning and Resource List](http://persistence.uni-leipzig.org/nlp2rdf/specification/version.html)  

## NIF Stanford Implementation
We are currently providing a maven web service for the stanford implementation.

### Compile and run
Note: you will need to install Maven

1. Download and unzip: https://github.com/NLP2RDF/software/archive/master.zip
2. go to folder java-maven
3. run '''mvn install'''
4. go to the folder '''software/java-maven/implementation/stanfordcorenlp/
5. run '''mvn jetty:run'''

The server will start at 8899 because the option '''-Djetty.port=8899''' is default and omitted.

you should be able to reach the service under:
'''curl http://localhost:8899/stanfordcorenlp/stanfordcorenlp?f=text&i=This+is+a+test.'''

### CLI
The -e option is Maven for extended error messages
    mvn compile exec:java -e  -Dexec.mainClass="org.nlp2rdf.implementation.stanfordcorenlp.StanfordCLI" -Dexec.args="-f text -i 'This is a test.' "

## NIF Validator
An up-to-date validator is supplied at root level in this repository and can be dowloaded here: http://persistence.uni-leipzig.org/nlp2rdf/specifications/validate.jar


### Use the Jar
```Shell
# the file is executable, so you can run either "./validate.jar" or "java -jar validate.jar"
# display help
java -jar validate.jar -h
# validate a turtle file
java -jar validate.jar --input=java-maven/core/jena/src/test/resources/nif-erroneous-model.ttl --informat=turtle  
# output in turtle
java -jar validate.jar --input=java-maven/core/jena/src/test/resources/nif-erroneous-model.ttl --informat=turtle --outformat=turtle
# short version -i turtle and -o text are default
java -jar validate.jar -i core/jena/src/test/resources/nif-erroneous-model.ttl 
java -jar validate.jar -i core/jena/src/test/resources/nif-erroneous-model.ttl -o turtle 
# test URLs
java -jar validate.jar -i http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core/example.ttl -intype url
```

### Start a server with Jetty and Maven
```Shell
cd java-maven
mvn clean install
cd core/jena
mvn jetty:run 
# -Djetty.port=8899 is default and omitted
```

### Use directly with Maven
```Shell
cd java-maven/core/jena
mvn compile exec:java -e  -Dexec.mainClass="org.nlp2rdf.cli.Validate" -Dexec.args="-i src/test/resources/nif-erroneous-model.ttl -o turtle"
```

### Build Validator Jar 
the jar appears in core/jena/target
```hell
cd java-maven/core/jena
mvn clean compile assembly:single
```
## Java-Maven implementation of NIF

### Jena
We started with an implementation using Jena. We are aware that this is quite a large dependency. Feel free to copy and paste any code into your project and adapt it.
We would be happy, if you were to share your code for NIF implementations using other RDF libraries. 

## Maven
Including the maven dependencies allows you to load the ontologies via 
```Java
String nif-core-ttl = "org/uni-leipzig/persistence/nlp2rdf/nif-core/nif-core.ttl" ;
String nif-core-owl = "org/uni-leipzig/persistence/nlp2rdf/nif-core/nif-core.owl" ;
String testsuite = "org/uni-leipzig/persistence/nlp2rdf/testcase/lib/nif-2.0-suite.ttl" ;
// get input stream
InputStream is = SPARQLValidator.class.getClassLoader().getResourceAsStream(testsuite);
// load the test cases into Jena or the RDF library of your choice
OntModel testsuite = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
testsuite.read(is, "", "N3");
```

### Repository
```XML
<repository>
    <id>maven.aksw.internal</id>
    <name>University Leipzig, AKSW Maven2 Repository</name>
    <url>http://maven.aksw.org/repository/internal</url>
</repository>
<repository>
    <id>maven.aksw.snapshots</id>
    <name>University Leipzig, AKSW Maven2 Repository</name>
    <url>http://maven.aksw.org/repository/snapshots</url>
</repository>
```
### Ontologies
Please look here for the latest versions: http://maven.aksw.org/archiva/browse/org.nlp2rdf.ontologies/persistence.uni-leipzig.org
```XML
<dependency>
    <groupId>org.nlp2rdf.ontologies</groupId>
    <artifactId>persistence.uni-leipzig.org</artifactId>
    <version>2.0.0-SNAPSHOT</version>
</dependency>
```

### Jena Implementation
Please look here for the latest versions: http://maven.aksw.org/archiva/browse/org.nlp2rdf.software/jena
```XML
<dependency>
      <groupId>org.nlp2rdf.software</groupId>
      <artifactId>jena</artifactId>
      <version>2.0.1-SNAPSHOT</version>
</dependency>
```

#PHP
There isn't much implemented, yet.
