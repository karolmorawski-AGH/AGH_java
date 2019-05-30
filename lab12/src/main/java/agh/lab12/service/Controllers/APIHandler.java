package agh.lab12.service.Controllers;

import agh.lab12.service.Entities.Place;
import agh.lab12.service.Entities.WeatherData;
import agh.lab12.service.Repositories.PlacesRepository;
import agh.lab12.service.Repositories.WeatherDataRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class APIHandler {

	@Autowired
	PlacesRepository placesRepository;

	@Autowired
	WeatherDataRepository temperatureRepository;

	private String apiKey = "1d273615b3a5d5f6ff6b55424b36d7b1";

	public String getAPIPath(String city){
		return "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + apiKey;
	}

	public String getAPIPath(String latitude, String longitude){
		return "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&units=metric&appid=" + apiKey;
	}

	public String getData(String path) {

		String json = null;
		try {
			URL url = new URL(path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("charset", "utf-8");
			connection.connect();
			InputStream inStream = connection.getInputStream();

			json = new Scanner(inStream, "UTF-8").useDelimiter("\\Z").next();
		} catch (IOException ex) {
			ex.printStackTrace();
			return "{}";
		}

		return json;

	}

	public void updateTemperature(int id) throws JSONException {

		Place place = placesRepository.findById(id).get();

		String receivedJSON = "";

		if (place.getCityname().length() != 0) {
			receivedJSON = getData(getAPIPath(place.getCityname()));
		} else {
			receivedJSON = getData(getAPIPath(place.getLat(), place.getLon()));
		}

		JSONObject json = new JSONObject(receivedJSON);

		Double val;
		try{
			val = json.getJSONObject("main").getDouble("temp");
		} catch	(JSONException e){
			val = -273.3;
		}

		WeatherData entity = new WeatherData(id, val);
		temperatureRepository.save(entity);
	}

	public Double getLast(int id, int count) throws Exception {

		if(count <= 0){
			throw new Exception();
		}

		Integer[] array = {id};
		Iterable<Integer> iterable = Arrays.asList(array);

		ArrayList<Double> values = new ArrayList<>();

		for (Iterator<WeatherData> iter = temperatureRepository.findAll().iterator(); iter.hasNext(); ) {
			WeatherData el = iter.next();
			if(el.getPlace_id() == id){
				values.add(el.getTemperature());
			}
		}
		List<Double> trimmed;
		if(values.size() > count) {
			trimmed = values.subList(values.size() - count, values.size());
		} else {
			trimmed = values;
		}

		Double average = 0.;
		for(int i=0; i<trimmed.size(); i++){
			average += trimmed.get(i);
		}

		average /= trimmed.size();
		return average;

	}

}
