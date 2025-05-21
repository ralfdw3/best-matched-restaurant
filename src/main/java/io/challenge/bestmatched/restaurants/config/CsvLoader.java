package io.challenge.bestmatched.restaurants.config;

import io.challenge.bestmatched.restaurants.entity.Cuisine;
import io.challenge.bestmatched.restaurants.entity.Restaurant;
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
import java.util.function.Function;

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
        try (Scanner scanner = new Scanner(inputStream)) {
            readAndIgnoreHeader(scanner);
            cuisineRepository.saveAll(readCsvFileToEntities(scanner, buildCuisineMapperFunction()));
        }
    }

    private void loadRestaurants() {
        final InputStream inputStream = getClass().getResourceAsStream("/restaurants.csv");
        try (Scanner scanner = new Scanner(inputStream)) {
            readAndIgnoreHeader(scanner);
            restaurantRepository.saveAll(readCsvFileToEntities(scanner, buildRestaurantMapperFunction()));
        }
    }

    private static void readAndIgnoreHeader(final Scanner scanner) {
        if (scanner.hasNextLine()) scanner.nextLine();
    }

    private static <T> List<T> readCsvFileToEntities(final Scanner scanner, final Function<String[], T> mapper) {
        final List<T> entities = new ArrayList<>();

        while (scanner.hasNextLine()) {
            final String line = scanner.nextLine();
            final String[] fields = line.split(",");
            entities.add(mapper.apply(fields));
        }

        return entities;
    }

    private static Function<String[], Cuisine> buildCuisineMapperFunction() {
        return fields -> Cuisine.builder()
                .id(parseLong(fields[0]))
                .name(fields[1])
                .build();
    }

    private Function<String[], Restaurant> buildRestaurantMapperFunction() {
        return fields -> Restaurant.builder()
                .name(fields[0])
                .customerRating(parseInt(fields[1]))
                .distance(parseInt(fields[2]))
                .price(new BigDecimal(fields[3]))
                .cuisine(cuisineRepository.findById(parseLong(fields[4])).orElseThrow())
                .build();
    }
}
