******************************************************************************
*  Copyright (C) 2010-2011, Sebastian Hellmann                               *
*                                                                            *
*  Licensed under the Apache License, Version 2.0 (the "License");           *
*  you may not use this file except in compliance with the License.          *
*  You may obtain a copy of the License at                                   *
*                                                                            *
*      http://www.apache.org/licenses/LICENSE-2.0                            *
*                                                                            *
*  Unless required by applicable law or agreed to in writing, software       *
*  distributed under the License is distributed on an "AS IS" BASIS,         *
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
*  See the License for the specific language governing permissions and       *
*  limitations under the License.                                            *
******************************************************************************

NIF - OpenNLP integration
=================

Tools to integrate NIF and OpenNLP. There's a wrapper that allows converting OpenNLP output to NIF, as well as parsers to stream OpenNLP objects from NIF files.

#### Wrapper

The OpenNLP wrapper allows annotating texts with OpenNLP models and generating NIF output. Annotation of **sentence boundaries**, **tokens** and **part of speech** are implemented at the moment. Please note that we don't distribute the models, so get them [from OpenNLP](http://opennlp.sourceforge.net/models-1.5/) or train them yourself.

In addition to the usual NIF API parameters, there is the parameter *modelFolder* which denotes the folder in which you have the OpenNLP models. The second new parameter is *language*, denoting the language of your models. It typically is a 2 character language code. We are assuming the models have the same naming convention as the official models (like en-token.bin; en-pos.bin etc). If there is no language code, the wrapper defaults to english. 

You can also set an output file via *outfile*.

#### CLI Example 

Annotate $file and print the results to $output.ttl

```Shell
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.implementation.opennlp.OpenNLPWrapperCLI" -Dexec.args="-intype file -f text -i $file -outfile $output.ttl -modelFolder $folder -u OffsetBasedString" 
```

Due to the use of Jena OntModel, the application is very memory intensive. You may want to use

```Shell
MAVEN_OPTS="-Xmx4000m -XX:+UseConcMarkSweepGC"
```

before running the application. Still, this application does not scale very well for large texts due to JENA model overhead.

#### Training your own models

To run OpenNLP, you need some models. If the existing models don't fit your language or domain, you might want to train your own ones from existing NIF corpora. To do this, run the corpus trainer like this:

```Shell
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.implementation.opennlp.train.OpenNLPModelTrainer" -Dexec.args="-intype url -f turtle -i http://brown.nlp2rdf.org/lod/a01.ttl --language=en --tasks=sentence,pos,token"
```

to train a model using an online NIF corpus, like [http://brown.nlp2rdf.org/lod/a01.ttl](http://brown.nlp2rdf.org/lod/a01.ttl). 

You can define which tasks are being performed using the *tasks* parameter. It takes a comma-separated list and currently supports tokenization,pos tagging and sentence detection. 

However, the corpus might be split into a number of smaller files and you want to train on the whole corpus. This is supported by using [DataID](https://github.com/dbpedia/dataid). 

Just use the [DataID of the corpus](http://brown.nlp2rdf.org/dataid.ttl) as input and set the *dataid* parameter to true like so:

```Shell
mvn exec:java -e  -Dexec.mainClass="org.nlp2rdf.implementation.opennlp.train.OpenNLPModelTrainer" -Dexec.args="-intype url -f turtle -i http://brown.nlp2rdf.org/dataid.ttl --language=en --dataid=true --tasks=sentence,pos,token"
```

The models are then written to the same folder using the standard naming convention $language-$task.bin.

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
