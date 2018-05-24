# WEBSEM

## XMLParser

· Java main() that parses the NYC open data XML file, containing all film data

· Asks [**OMDB API**](http://www.omdbapi.com/) to gather more data

· Compile both datasources into one single, more complete data

· Insert all triples into SPARQL database, using **Fuseki**


## Using Fuseki

Fuseki is a SPARQL Database server, which you can run localy.

· [You can download it here](https://jena.apache.org/download/#jena-fuseki)

· Once you started your Fuseki server, go to *localhost:3030* and input the **Ontology** 

· You can find the Ontology in the resources folder on **Github** : [NYC_Ontology.owl](https://github.com/JeanBrice/WEBSEM/blob/master/resources/NYC_ontology.owl)

· Before running the Java main(), do not forget to user your own OMDB API key

· Once you have your database running, you only need to run the Java main() to populate the database, and to run the **node.js server**


## Application

From root folder, run
· npm install

· node server.js

In your favourite browser:

· localhost:8080