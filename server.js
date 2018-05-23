// server.js

// BASE SETUP ============================================================
var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var path = require('path');
var router = express.Router();

// DATABASE CONFIGURATION ================================================
const SparqlClient = require('sparql-client-2');
const SPARQL = SparqlClient.SPARQL;
const endpoint = ''; // OUR DATABASE

// CONFIGURATION =========================================================
var port = process.env.PORT || 8080;

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// START THE SERVER ======================================================
var server = app.listen(port, () => {
    console.log('Server listening on port : ' + port);
});

// ROUTES CONFIGURATION FOR OUR API ======================================
app.use('/api', router);

router.get('/', (req, res) => {
    res.json({ message: 'Welcome to the WEBSEM PROJECT API!' });
});

// ROUTE FOR /scenes -----------------------------------------------------
app.get('/api/scenes', (req, res) => {
    
    // Get all scenes and the film name associated to it
    var query =
        SPARQL  `PREFIX: <http://www.semanticweb.org/localuser/ontologies/2018/4/untitled-ontology-5/>
                SELECT ?title ?scene ?lat ?long
                WHERE{
                    ? scene : takesPlaceIn : New_York .
                    ?film : contains ?scene .
                    ?film : has_for_title ?title .
                    ?scene : has_longitude ?long .
                    ?scene : has_latitude ?lat
                }`
    
    var client = new SparqlClient(endpoint);
        // .register({ db: 'http://dbpedia.org/resource/' })
        // .register({ dbpedia: 'http://dbpedia.org/property/' });

    client.query(query)
        .execute()
        .then(function (results) {
            console.dir(results, { depth: null });
            res.json(results);
        })
        .catch(function (error) {
            // Oh no! 🙀
            console.log(error);
        });

});

// ROUTE FOR /films/:film_title ------------------------------------------
app.get('/api/films/:film_title', (req, res) => {
    res.json({ message: 'Welcome to the WEBSEM PROJECT API!' });
});

// SERVE STATIC FILES 
app.use(express.static(path.join(__dirname, 'front/')));

// FRONTEND ROUTE ========================================================
app.get('*', (req, res) => {
    res.sendFile(__dirname + '/front/index.html');
});
