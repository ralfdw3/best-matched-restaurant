package io.challenge.bestmatched.restaurants.repository;

import io.challenge.bestmatched.restaurants.entity.Restaurant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAll(final Specification<Restaurant> specification);
}
