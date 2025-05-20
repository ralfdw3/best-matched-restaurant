package io.challenge.bestmatched.restaurants.config;

import io.challenge.bestmatched.restaurants.model.Cuisine;
import io.challenge.bestmatched.restaurants.model.Restaurant;
import io.challenge.bestmatched.restaurants.repository.CuisineRepository;
import io.challenge.bestmatched.restaurants.repository.RestaurantRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@Configuration
@RequiredArgsConstructor
public class CsvLoader {
    private final RestaurantRepository restaurantRepository;
    private final CuisineRepository cuisineRepository;

    @PostConstruct
    public void loadDatabase() {
        loadCuisines();
        loadRestaurants();
    }

    private void loadCuisines() {
        final InputStream inputStream = getClass().getResourceAsStream("/cuisines.csv");
        final List<Cuisine> cuisines = new ArrayList<>();
        try (Scanner scanner = new Scanner(inputStream)) {
            if (scanner.hasNextLine()) scanner.nextLine();
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                final String[] fields = line.split(",");
                final Cuisine cuisine = Cuisine.builder()
                        .id(parseLong(fields[0]))
                        .name(fields[1])
                        .build();

                cuisines.add(cuisine);
            }
        }
        cuisineRepository.saveAll(cuisines);
    }

    private void loadRestaurants() {
        final InputStream inputStream = getClass().getResourceAsStream("/restaurants.csv");
        final List<Restaurant> restaurants = new ArrayList<>();
        try (Scanner scanner = new Scanner(inputStream)) {
            if (scanner.hasNextLine()) scanner.nextLine();
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                final String[] fields = line.split(",");
                final Restaurant restaurant = Restaurant.builder()
                        .name(fields[0])
                        .customerRating(parseInt(fields[1]))
                        .distance(parseInt(fields[2]))
                        .price(new BigDecimal(fields[3]))
                        .cuisine(cuisineRepository.findById(parseLong(fields[4])).orElseThrow())
                        .build();

                restaurants.add(restaurant);
            }
        }
        restaurantRepository.saveAll(restaurants);
    }
}
