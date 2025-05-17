package io.challenge.bestmatched.restaurants.service;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantInput;
import io.challenge.bestmatched.restaurants.dto.SearchRestaurantOutput;
import io.challenge.bestmatched.restaurants.repository.CuisineRepository;
import io.challenge.bestmatched.restaurants.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchRestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final CuisineRepository cuisineRepository;

    public List<SearchRestaurantOutput> searchRestaurants(final SearchRestaurantInput input) {

    }
}
