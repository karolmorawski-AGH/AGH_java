package agh.lab12.service.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="places")
public class Place implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String city_name;
	private String latitude;
	private String longitude;
	private int frequency;

	public Place() {
	}

	public Place(String city_name, String latitude, String longitude, int frequency) {
		this.city_name = city_name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return "{" +
				"\"id\" : \"" + id +
				"\", \"city_name\" : \"" + city_name +
				"\", \"latitude\" : \"" + latitude +
				"\", \"longitude\" : \"" + longitude +
				"\", \"frequency: \"" + frequency +
				"\"}";
	}

	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityname() {
		return city_name;
	}

	public void setCityname(String cityname) {
		this.city_name = cityname;
	}

	public String getLon() {
		return longitude;
	}

	public void setLon(String lon) {
		this.longitude = lon;
	}

	public String getLat() {
		return latitude;
	}

	public void setLat(String lat) {
		this.latitude = lat;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}
