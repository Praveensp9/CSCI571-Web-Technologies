function iconImage(icon) {
		        if (icon == "clear-day") {
		            return "https://cdn3.iconfinder.com/data/icons/weather-344/142/sun-512.png";
		        }
		        else if (icon == "clear-night") {
		            return "https://cdn3.iconfinder.com/data/icons/weather-344/142/sun-512.png";
		        }
		        else if (icon == "rain") {
		            return "https://cdn3.iconfinder.com/data/icons/weather-344/142/rain-512.png";
		        }
		        else if (icon == "snow") {
		            return "https://cdn3.iconfinder.com/data/icons/weather-344/142/snow-512.png";
		        }
		        else if (icon == "sleet") {
		            return "https://cdn3.iconfinder.com/data/icons/weather-344/142/sleet-512.png";
		        }
		        else if (icon == "wind") {
		            return "https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_10-512.png";
		        }
		        else if (icon == "fog") {
		            return "https://cdn3.iconfinder.com/data/icons/weather-344/142/cloudy-512.png";
		        }
		        else if (icon == "cloudy") {
		            return "https://cdn3.iconfinder.com/data/icons/weather-344/142/cloud-512.png";
		        }
		        else if (icon == "partly-cloudy-day") {
		            return "https://cdn3.iconfinder.com/data/icons/weather-344/142/sunny-512.png";
		        }
		        else if (icon == "partly-cloudy-night") {
		            return "https://cdn3.iconfinder.com/data/icons/weather-344/142/sunny-512.png";
		        }
		        else {
		            return "NA";
		        }
   		 }

function showCard(json,timezone,city) {

    // Header
    var d = new Date(json.currently.time*1000).toLocaleString("en-US", {timeZone: timezone});
		d= new Date(d);
    var day = d.getDate();
    var month = d.getMonth()+1;
    var year = d.getFullYear();

    var html = "<span style='color: black;'>"+day+"/"+month+"/"+year+"</span>";

    document.getElementById("head").insertAdjacentHTML('afterbegin',html);


    // Body
    var icon = json.currently.icon;
    var image = iconImage(icon);
    switch(icon){
        case "clear-day": icon = "Clear";break;
        case "clear-night": icon = "Clear Night";break;
        case "rain": icon = "Rain"; break;
        case "snow": icon = "Snow"; break;
        case "sleet": icon = "Sleet"; break;
        case "wind": icon = "Wind"; break;
        case "fog": icon = "Fog"; break;
        case "cloudy": icon = "Cloudy"; break;
        case "partly-cloudy-day": icon = "Partly Cloudy Day"; break;
        case "partly-cloudy-night": icon = "Partly Cloudy Night"; break;
    }

    var text = "<span id='modc' style='padding-left:15px;font-size:25px;font-weight:400;'>"+city+"</span><br>";
    text += "<span style='padding-left:15px;font-size:35px;font-weight:400;'>"+Math.round(json.currently.temperature)+"<span class='degi' style='position: absolute;top: 33px;left: 70px;'><img src='https://cdn3.iconfinder.com/data/icons/virtual-notebook/16/button_shape_oval-512.png' width='11' height='11'></span><span class='degree' style='font-size: 34px;padding-left: 21px;'>F</span></span><br>";
    text += "<span style='padding-left:15px;'>"+icon+"</span><br>";
    text += "<span style='position: absolute;top: 40px;left: 240px;'><image src='"+image+"' width='80' height='80'></span>";

    document.getElementById("mbody").innerHTML = text;

    // Footer
    var precipitation = json.currently.precipIntensity;
    var temp = Math.round(json.currently.precipIntensity);
    if(temp > 0){
      precipitation = precipitation.toFixed(2);
    }
    else{
      precipitation = temp;
    }
    var rain = json.currently.precipProbability;
    var wind = json.currently.windSpeed;
  	var humid = json.currently.humidity;
  	var vis = json.currently.visibility;


    let med = window.matchMedia( "(max-width: 700px)" );
    if(med.matches){
      	// console.log("700");
        var text1 = "<div class='container' id='id400' style='padding-left: 29px;'>";
        text1 += "<div class='row'><span> Precipitaion: <span>"+precipitation+"</span></span></div>";
        text1 += "<div class='row'><span> Chance of Rain: <span>"+Math.round(rain*100)+"<span id='id61'> %</span></span></span></div>";
        text1 += "<div class='row'><span> WindSpeed: <span>"+wind.toFixed(2)+" <span id='id61'> mph</span></span></span></div>";
        text1 += "<div class='row'><span> Humidity: <span>"+Math.round(humid*100)+"<span id='id61'> %</span></span></span></div>";
        text1 += "<div class='row'><span> Visibility: <span>"+Math.round(vis)+"<span id='id61'> miles</span></span></span></div>";
        text1 += "</div>"
    }
    else{
      // console.log("less than 700");
      var text1 = "<div class='container' id='id400' style='padding-left: 224px;'>";
      text1 += "<div class='row'><span> Precipitaion: <span>"+precipitation+"</span></span></div>";
      text1 += "<div class='row'><span> Chance of Rain: <span>"+Math.round(rain*100)+"<span id='id61'> %</span></span></span></div>";
      text1 += "<div class='row'><span> WindSpeed: <span>"+wind.toFixed(2)+" <span id='id61'> mph</span></span></span></div>";
      text1 += "<div class='row'><span> Humidity: <span>"+Math.round(humid*100)+"<span id='id61'> %</span></span></span></div>";
      text1 += "<div class='row'><span> Visibility: <span>"+Math.round(vis)+"<span id='id61'> miles</span></span></span></div>";
      text1 += "</div>"
    }

    document.getElementById("foot").innerHTML = text1;
}

function showChart(){
  document.getElementById('chartContainer').innerHTML = '';
}

function myfav(){
  document.getElementById('fav').style.display = "block";
}

function ddd(json,timezone){
  var d = new Date(json*1000).toLocaleString("en-US", {timeZone: timezone});
  d= new Date(d);
  var day = d.getDate();
  var month = d.getMonth()+1;
  var year = d.getFullYear();

  return day+"/"+month+"/"+year;
}
