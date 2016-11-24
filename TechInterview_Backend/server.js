var http = require('http');
var express = require('express');
var app = express();
var formidable = require('formidable');
var util = require('util');
var fs = require('fs');
var Regex = require('regex');
var cookieParser = require('cookie-parser')
var sql = require('mssql');
var session = require('client-sessions');
var validator = require('validator');

var mysql = require('mysql');

var connectionpool = mysql.createPool({
	connectionLimit : 10,
	host : 'localhost',
	user : 'root',
	password: 'qweasd',
	database: 'techinterview'
});

var server = http.createServer(app);

//Setting up the Session
app.use(session({
  cookieName: 'session',
  secret: 'random_string_goes_here',
  duration: 30 * 60 * 1000,
  activeDuration: 5 * 60 * 1000,
}));

//teszt lekerdezes a mysql adatbazisunkbol
app.get('/positions', function(request, response){

	var query = 'SELECT * FROM position';

	connectionpool.query(query, function(err, rows, fields){
		if(err){
			console.log(err.message);
		}else{
			response.send(rows);
		}
	});

});

//kerdoiv generalasa, palyazott pozicio szerint
app.get("/questionnaire/:position",function(request,response){
	
	//XSS escaping
	var position = validator.escape(request.params.position);
	//All values are automatically sanitized against sql injection.
	var queryString = `SELECT * FROM questions WHERE idposition = \'${position}\'`;

	connectionpool.query(queryString, function(err, rows, fields){
		if(err){
			console.log(err.message);
		}else{
			response.send(rows);
		}
	});
});



/*//Creating the connection pool object
var connection = new sql.Connection("mssql://username:password@localhost/SportCenter");
connection.connect();

//The '/login.html' route - is used, when login button is clicked
app.post('/login.html',function( request, response) {
	var form = new formidable.IncomingForm();
	form.parse(request, function( err, fields, files ) {
		var formData = util.inspect(fields);
		var formDataFields = formData.split(",");
		
		//XSS escaping
		var username = validator.escape(formDataFields[0].substring(formDataFields[0].indexOf("'")+1,formDataFields[0].lastIndexOf("'")));
		var password = validator.escape(formDataFields[1].substring(formDataFields[1].indexOf("'")+1,formDataFields[1].lastIndexOf("'")));
		
		//All values are automatically sanitized against sql injection.
		var queryString = `select * from Users where Username = \'${username}\' AND PassW = \'${password}\'`;
	
		new sql.Request(connection).query(queryString).then(function(recordset) {
		
			// storing username in session
			request.session.user = username;
		
			var group = recordset[0].GroupID;
			if(group == 1){	//admin
				response.send('admin');
			}
			else{	//user
				response.send('user');
			}
		}).catch(function(err) {
			response.send('Incorrect username or password');
		});
	})
});

//*************************************************************************************

// HallName - ek kuldese a HallName <Select> - be
app.get('/getHallNameSelect',function( request, response) {
	
	//All values are automatically sanitized against sql injection.
	var queryString = 'select HallName from Halls';
	
	new sql.Request(connection).query(queryString).then(function(recordset) {
		
		var hallNames = '';
		
		for (i = 0; i < recordset.length; ++i) {
			hallNames += recordset[i].HallName + '/';
		}
		
		response.send(hallNames);
		
	}).catch(function(err) {
		response.send(err);
	});
});

// HallName - ek kuldese a HallName <Select> - be
app.get('/test',function( request, response) {
	
	response.send("Hello World");
});*/


app.use(express.static(__dirname));

server.listen(8888);