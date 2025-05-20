package io.challenge.bestmatched.restaurants.repository;

import io.challenge.bestmatched.restaurants.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long> {
}
