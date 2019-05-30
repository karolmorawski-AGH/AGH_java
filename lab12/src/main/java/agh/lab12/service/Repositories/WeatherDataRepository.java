package agh.lab12.service.Repositories;


import agh.lab12.service.Entities.WeatherData;
import org.springframework.data.repository.CrudRepository;

public interface WeatherDataRepository extends CrudRepository<WeatherData, Integer> {

}