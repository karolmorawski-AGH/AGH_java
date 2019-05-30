package agh.lab12.service.Repositories;

import agh.lab12.service.Entities.Place;
import org.springframework.data.repository.CrudRepository;

public interface PlacesRepository extends CrudRepository<Place, Integer> {

}
