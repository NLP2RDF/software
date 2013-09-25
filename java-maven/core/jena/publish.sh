# sync run
mvn clean assembly:single
mv target/*.jar validate.jar
rsync -rav validate.jar nlp2rdf@www.uni-leipzig.de:/data/homewww/nlp2rdf/webdir/specification/validate.jar

 


