package io.challenge.bestmatched.restaurants.repository;

import io.challenge.bestmatched.restaurants.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAll(final Specification<Restaurant> specification);
}
