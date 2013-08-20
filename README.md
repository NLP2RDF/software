# Software
This repository contains software and tools using and implementing NIF 2.0.
* The [NLP2RDF/NIF project page] (http://nlp2rdf.org) provides an overview.
* See here for an overview of resources: [License, Persistence, Versioning and Resource List](http://persistence.uni-leipzig.org/nlp2rdf/specification/version.html)  

## NIF Validator
An up-to-date validator is supplied at root level: validate.jar
Parameters are specified here: http://persistence.uni-leipzig.org/nlp2rdf/specifications/api.html
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
```

### Use directly with Maven
```Shell
cd java-maven/core/jena
mvn compile exec:java -e  -Dexec.mainClass="org.nlp2rdf.cli.Validate" -Dexec.args="-i nif-erroneous-model.ttl -o turtle"
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


