package io.challenge.bestmatched.restaurants.repository;

import io.challenge.bestmatched.restaurants.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineRepository extends JpaRepository<Cuisine, Long> {
}
