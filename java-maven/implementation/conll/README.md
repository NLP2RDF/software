ConLL09 Dependency Annotated Corpora to NIF
=================

Transforms a file or string in ConLL09 dependency tree format ([http://ufal.mff.cuni.cz/conll2009-st/task-description.html](http://ufal.mff.cuni.cz/conll2009-st/task-description.html)) to the NIF format.

Output will be one NIF file with one nif:Context element for the whole input text.

#### Parameters

In addition to the usual NIF API parameters, there is the parameter *tagset* that can be used to set the tagset used by the corpus. Choose one of the OLiA tagsets implemented [here](https://github.com/NLP2RDF/software/tree/master/java-maven/vocabularymodule/OLiA/src/main/java/org/nlp2rdf/vm/olia/models).

You can also set an output file via *outfile*
 

#### Example 

```Shell
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.implementation.conll.ConLLToNIFCLI" -Dexec.args="-intype file -f text -i $conllfile.conll -tagset Stts -outfile $conllfile.ttl" 
```

Due to the use of Jena OntModel, the application is very memory intensive. You may want to use

```Shell
MAVEN_OPTS="-Xmx4000m -XX:+UseConcMarkSweepGC"
```

before running the application.


#### Data

Most data adhering to the format is licensed, so please refer to the respective owners of the data. A major resource available is the German [Tiger Corpus](http://www.ims.uni-stuttgart.de/forschung/ressourcen/korpora/tiger.html) that is free for research use.
