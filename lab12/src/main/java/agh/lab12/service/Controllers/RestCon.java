package agh.lab12.service.Controllers;

import agh.lab12.service.Entities.Place;
import agh.lab12.service.Jobs.UpdateWeatherData;
import agh.lab12.service.Repositories.PlacesRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class RestCon {

	@Autowired
	PlacesRepository placesRepository;

	@Autowired
	APIHandler APIHander;

	/*[POST] /api/register
	Przyjmuje położenie geograficzne, lub nazwę miasta oraz częstotliwość
	sprawdzania temperatury. Dodaje nową lokalizację do bazy. Zwraca
	encję z identyfikatorem
	*/

	@RequestMapping(value = "/api/register", method = RequestMethod.POST)
	public ResponseEntity<Place> createEntry(@RequestBody String jsonString, HttpServletResponse response) throws JSONException, IOException, SchedulerException {

		JSONObject json = new JSONObject(jsonString);

		/*
		{
			"lat" : "50.0646501",
			"long" : "19.9449799",
			"freq" : 15
		}

		OR
		{
			"cityname" : "Krakow",
			"freq" : 15
		}
		*/

		String cityname = "";
		String lat = "";
		String longt = "";
		int freq;

		try {
			cityname = json.getString("cityname");
		} catch (JSONException e){
			cityname = "";
			try {
				lat = json.getString("lat");
				longt = json.getString("long");
				System.out.println("Parsed: " + lat + " " + longt);
			} catch (JSONException ex){
				//both city name and lat & long are incorrect
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

		}

		try {
			freq = json.getInt("freq");
		} catch (JSONException e){
			freq = 99999;
		}

		Place entity = new Place(cityname, lat, longt, freq);
		placesRepository.save(entity);

		APIHander.updateTemperature(entity.getId());

		//Trigger
		JobDetail job = JobBuilder.newJob(UpdateWeatherData.class).build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("UpdateWeatherData").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(freq).repeatForever()).build();

		//Scheduler
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		scheduler.start();
		scheduler.getContext().put("id", entity.getId());
		scheduler.scheduleJob(job, trigger);

		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	//[GET] /api/stat/{ID}/{n}
	@RequestMapping(value = "/api/stat/{id}/{count}", method = RequestMethod.GET)
	public ResponseEntity<String> getAverage(@PathVariable int id, @PathVariable int count) throws Exception {
		String json;
		try {
			json = "{\"average\" : \"" + APIHander.getLast(id, count) + "\"}";
		} catch (Exception e){
			return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(json, HttpStatus.OK);
	}


}
