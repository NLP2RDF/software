NIF - OpenNLP integration
=================

Tools to integrate NIF and OpenNLP. There's a wrapper that allows converting OpenNLP output to NIF, as well as parsers to stream OpenNLP objects from NIF files.

#### Wrapper

The OpenNLP wrapper allows annotating texts with OpenNLP models and generating NIF output. Annotation of **sentence boundaries**, **tokens** and **part of speech** are implemented at the moment. Please note that we don't distribute the models, so get them [from OpenNLP](http://opennlp.sourceforge.net/models-1.5/)

In addition to the usual NIF API parameters, there is the parameter *modelFolder* which denotes the folder in which you have the OpenNLP models. The second new parameter is *language*, denoting the language of your models. It typically is a 2 character language code. We are assuming the models have the same naming convention as the official models (like en-token.bin; en-pos-maxent.bin etc). If there is no language code, the wrapper defaults to english. 

You can also set an output file via *outfile*.

This class also enables you to **add spans** you generated via OpenNLP to a NIF model, via the addSpans() method. It takes a Span array, the text the spans cover as well as their desired OntClass and adds them to a JENA OntModel

#### CLI Example 

Annotate $file and print the results to $output.ttl

```Shell
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.implementation.opennlp.OpenNLPWrapperCLI" -Dexec.args="-intype file -f text -i $file -outfile $output.ttl -modelFolder $folder" 
```

Due to the use of Jena OntModel, the application is very memory intensive. You may want to use

```Shell
MAVEN_OPTS="-Xmx4000m -XX:+UseConcMarkSweepGC"
```

before running the application. Still, this application does not scale very well for large texts due to JENA model overhead.

#### Web Service Example 
In order to run the opennlp NIF web service you have to start the jetty plugin. Thus, navigate to the OpenNLP and start the plugin:

```Shell
mvn jetty:run

```
You are now able use the `curl` or use your favorite navigator to get the OpenNLP output.
The example above shows a URL which contain the the mandatories paramenters:

http://localhost:8899/opennlp/opennlp?f=text&i=This+is+a+test.&t=direct&modelFolder=model

Notice that the `modelFolder` is the path to the folder that contains models from [from OpenNLP](http://opennlp.sourceforge.net/models-1.5/). In the example, there is a folder called `model` located at OpenNLP directory.

#### Parsers

NIF Parsers enable you to read NIF files and generate OpenNLP objects from them. You can, for example, read OpenNLP **SentenceSamples** from a NIF file and use them to train a model:

```Java
//create a JENA model
Model model = ModelFactory.createDefaultModel();
//read the NIF file
model.read(new BufferedReader(new FileReader("corpusFile")),null,"TURTLE");
//create a new stream that reads the all sentences from context resources
TypedRDFResourceStream res = new TypedRDFResourceStream(model, NIFOntClasses.Context.getOntClass(model));
//create a SampleStream from the NIF corpus
ObjectStream<SentenceSample> sampleStream = new NIFSentenceSampleStream(res);
//train the model
char[] eos = ".?!".toCharArray();
SentenceDetectorFactory sdFactory = SentenceDetectorFactory.create(null, "en", true, null, eos);
SentenceModel sentmodel = SentenceDetectorME.train("en",sampleStream, sdFactory, null);
```

The parsers and their factories are following the OpenNLP SampleStream conventions.
