//Importing dependencies
const express = require('express');
const bodyParser = require('body-parser');
var path = require('path');
var request = require('request');
var qs = require('querystring');

var MyJson = {};

//Starting Express app
const app = express();


//Set the base path to the angular-test dist folder
app.use(express.static(path.join(__dirname, '/dist/myproject')));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}) );


//Any routes will be redirected to the angular app
app.get('*', function(req, res) {
    res.sendFile(path.join(__dirname, '/dist/myproject/index.html'));

});


app.all("/*", function(req, res, next){
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE,OPTIONS');
  res.header('Access-Control-Allow-Headers', 'Content-Type, Authorization, Content-Length, X-Requested-With');
  next();
});

// function getData(lat,long){
//  console.log("Lat : "+lat);
//  console.log("Long: "+long);
//  var forecastAPIKEY = "1cc202806d3c14a072088deac625b879";
//  url = "https://api.forecast.io/forecast/"+forecastAPIKEY+"/"+lat+","+long+"?exclude=flags";
//  request({
//     url: "https://api.forecast.io/forecast/"+forecastAPIKEY+"/"+lat+","+long+"?exclude=flags",
//     method: "GET",
//     json: true
// 	}, 
//   function (error, response, body){
// 	    //console.log(body.currently);
// 	    MyJson.data = body;
//   });	  
// }

app.post('/ping', function (req, res) {
  	console.log("Inside Ping of Server");

	var Street = req.body.Street;
	var City = req.body.City;
	var State = req.body.State;
	var location = req.body.location;

  	request({
	    url: "https://maps.googleapis.com/maps/api/geocode/json?address="+Street+","+City+","+State+"&key=AIzaSyAlWu-oapRa27KW0L1S8mVuKv9MCZ3_MGw",
	    method: "POST",
	    json: true
	}, 
	function (error, response, body){
			lat = body.results[0].geometry.location.lat;
			long = body.results[0].geometry.location.lng;

					var forecastAPIKEY = "1cc202806d3c14a072088deac625b879";
					// url = "https://api.forecast.io/forecast/"+forecastAPIKEY+"/"+lat+","+long+"?exclude=flags";
					request({
						url: "https://api.forecast.io/forecast/"+forecastAPIKEY+"/"+lat+","+long+"?exclude=flags",
						method: "GET",
						json: true
					}, 
					function (error, response, body){
						res.send(body);
					});
	});
  
})



//Starting server on port 8081
app.listen(8081, () => {
    console.log('Server started!');
    console.log('on port 8081');
});