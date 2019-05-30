package agh.lab12.service.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="data")
public class WeatherData implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int place_id;
	private Double temperature;

	public WeatherData(int place_id, Double temperature) {
		this.place_id = place_id;
		this.temperature = temperature;
	}

	public WeatherData(){

	}

	public Double getTemperature() {
		return temperature;
	}

	public int getPlace_id() {
		return place_id;
	}
}
