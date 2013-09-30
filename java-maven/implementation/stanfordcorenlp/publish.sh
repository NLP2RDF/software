# sync run
mvn clean compile assembly:single
mv target/*.jar nif-2.0-stanfordcorenlp-cli-beta.jar
rsync -rav nif-2.0-stanfordcorenlp-cli-beta.jar root@prod1:/var/www/nlp2rdf_data/nlp2rdf_static/releases/nif-2.0-stanfordcorenlp-cli-beta.jar

 
