package com.co.sofka.back.Repository;

import com.co.sofka.back.Models.Coordinates;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CoordinatesRepository extends MongoRepository<Coordinates, String> {

}
