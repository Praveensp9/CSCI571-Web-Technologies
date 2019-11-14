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
// app.get('*', function(req, res) {
//     res.sendFile(path.join(__dirname, '/dist/myproject/index.html'));

// });


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
  	//console.log("Inside Ping of Server");
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
				console.log("ping results : "+response.body.status);
				if(response.body.status == "OK"){
					lat = body.results[0].geometry.location.lat;
					long = body.results[0].geometry.location.lng;

					// var forecastAPIKEY = "1cc202806d3c14a072088deac625b879";
					var forecastAPIKEY = "76810dba2173f848dc41c22412d39d1a"; 
					// console.log("https://api.forecast.io/forecast/"+forecastAPIKEY+"/"+lat+","+long+"?exclude=flags");
					// url = "https://api.forecast.io/forecast/"+forecastAPIKEY+"/"+lat+","+long+"?exclude=flags";
					request({
						url: "https://api.forecast.io/forecast/"+forecastAPIKEY+"/"+lat+","+long+"?exclude=flags",
						method: "GET",
						json: true
					}, 
					function (error, response, body){
						res.send(body);
					});
				}
				else{
					res.send("error");
				}

	});
})

app.post('/favorite', function (req, res) {
  	// console.log("Inside favorite of Server");

	var lat = req.body.lat;
	var long = req.body.lng;

	// var forecastAPIKEY = "1cc202806d3c14a072088deac625b879";
	var forecastAPIKEY = "76810dba2173f848dc41c22412d39d1a"; 
	request({
		url: "https://api.forecast.io/forecast/"+forecastAPIKEY+"/"+lat+","+long+"?exclude=flags",
		method: "GET",
		json: true
	}, 
	function (error, response, body){
		res.send(body);
	});

})

// https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+val+"&types=(cities)&language=en&key=AIzaSyD7A2X-aUKOIqUP9Rzmw3lkK_wKlFjXzBs

app.post('/autocomplete', function (req, res) {
  	// console.log("Inside autocomplete of Server");
	var val = req.body.val
	request({
				url: "https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+val+"&types=(cities)&language=en&key=AIzaSyD7A2X-aUKOIqUP9Rzmw3lkK_wKlFjXzBs",
				method: "GET",
				json: true
			}, 
	function (error, response, body){
		res.send(body);
	});
})

// https://www.googleapis.com/customsearch/v1?q=[California]%20State%20Seal&cx=010150538396842669718:st06uz4ggld&imgSize=huge&imgType=news&num=1&searchType=image&key=AIzaSyCYFZDsaZUoLWOoRUlaJztaC15hdLwhXdc 
// https://www.googleapis.com/customsearch/v1?q=[texas]%20State%20Seal&cx=010150538396842669718:st06uz4ggld&imgSize=huge&imgType=news&num=1&searchType=image&key=AIzaSyCYFZDsaZUoLWOoRUlaJztaC15hdLwhXdc
// "v1?q=Seal%20of%20$[STATE]%20state&cx=[Engine_ID]&imgSize=large&imgType=news&num=1&searchType=image&key=[KEY]" and it seems to work pretty well
// "https://www.googleapis.com/customsearch/v1?q=Seal%20of%20$["+state+"]%20state&cx=010150538396842669718:st06uz4ggld&imgSize=huge&imgType=news&num=1&searchType=image&key=AIzaSyCYFZDsaZUoLWOoRUlaJztaC15hdLwhXdc"
// console.log("https://www.googleapis.com/customsearch/v1?q=Seal%20of%20$["+state+"]%20state&cx=010150538396842669718:st06uz4ggld&imgSize=large&imgType=news&num=1&searchType=image&key=AIzaSyCYFZDsaZUoLWOoRUlaJztaC15hdLwhXdc");

app.post('/logo', function (req, res) {
  	// console.log("Inside logo of Server");
	var state = req.body.state;
	
	request({
				url: "https://www.googleapis.com/customsearch/v1?q=Seal%20of%20$["+state+"]%20state&cx=010150538396842669718:st06uz4ggld&imgSize=large&imgType=news&num=1&searchType=image&key=AIzaSyCYFZDsaZUoLWOoRUlaJztaC15hdLwhXdc",
				method: "GET",
				json: true
			}, 
	function (error, response, body){
				res.send(body);
	});
})

app.post('/current', function (req, res) {
  	//console.log("Inside Current of Server");

	var lat = req.body.lat;
	var lon = req.body.lon;

	// var forecastAPIKEY = "1cc202806d3c14a072088deac625b879";
	var forecastAPIKEY = "76810dba2173f848dc41c22412d39d1a";
	url = "https://api.forecast.io/forecast/"+forecastAPIKEY+"/"+lat+","+lon+"?exclude=flags";

	request({
				url: "https://api.forecast.io/forecast/"+forecastAPIKEY+"/"+lat+","+lon+"?exclude=flags",
				method: "GET",
				json: true
			}, 
	function (error, response, body){
		res.send(body);
	});
})

app.post('/daily', function (req, res) {
  	// console.log("Inside daily of Server");

	var lat = req.body.lat;
	var lon = req.body.lon;
	var time = req.body.time;

	// var forecastAPIKEY = "1cc202806d3c14a072088deac625b879";
	var forecastAPIKEY = "76810dba2173f848dc41c22412d39d1a"; 
	url = "https://api.forecast.io/forecast/"+forecastAPIKEY+"/"+lat+","+lon+","+time+"?exclude=flags";

	request({
				url: url,
				method: "GET",
				json: true
			}, 
	function (error, response, body){
		res.send(body);
	});
})

// http://localhost:8081/weathersearch?street=1210&city=Los%20Angeles&state=California

app.get('/weathersearch', function(req, res) {
	var Street = req.query.street;
	var City = req.query.city;
	var State = req.query.state;

	request({
	    url: "https://maps.googleapis.com/maps/api/geocode/json?address="+Street+","+City+","+State+"&key=AIzaSyAlWu-oapRa27KW0L1S8mVuKv9MCZ3_MGw",
	    method: "POST",
	    json: true
	}, 
	function (error, response, body){
				console.log("ping results : "+response.body.status);

				lat = body.results[0].geometry.location.lat;
				long = body.results[0].geometry.location.lng;

				// var forecastAPIKEY = "1cc202806d3c14a072088deac625b879";
				var forecastAPIKEY = "76810dba2173f848dc41c22412d39d1a"; 
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