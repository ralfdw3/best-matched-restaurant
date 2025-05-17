package io.challenge.bestmatched.restaurants.repository;

import io.challenge.bestmatched.restaurants.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
