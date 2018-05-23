// server.js

// BASE SETUP ============================================================
var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var path = require('path');

// CONFIGURATION =========================================================
var port = process.env.PORT || 8080;

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// START THE SERVER ======================================================
var server = app.listen(port, () => {
    console.log('Server listening on port : ' + port);
});

// ROUTES CONFIGURATION FOR OUR API ======================================
// require('./app/routes/routes')(app, io);

// SERVE STATIC FILES 
app.use(express.static(path.join(__dirname, 'front/')));

// FRONTEND ROUTE ========================================================
app.get('*', (req, res) => {
    res.sendFile(__dirname + '/front/index.html');
});
