package io.challenge.bestmatched.restaurants.util;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantInput;
import io.challenge.bestmatched.restaurants.dto.SearchRestaurantInput.SearchRestaurantInputBuilder;
import io.challenge.bestmatched.restaurants.dto.SearchRestaurantOutput;
import io.challenge.bestmatched.restaurants.dto.SearchRestaurantOutput.SearchRestaurantOutputBuilder;
import io.challenge.bestmatched.restaurants.entity.Cuisine;
import io.challenge.bestmatched.restaurants.entity.Cuisine.CuisineBuilder;
import io.challenge.bestmatched.restaurants.entity.Restaurant;
import io.challenge.bestmatched.restaurants.entity.Restaurant.RestaurantBuilder;

import java.math.BigDecimal;

import static java.math.BigDecimal.TEN;

public class DataFactory {
    public static final long LONG_ONE = 1L;
    public static final int INT_ONE = 1;
    public static final int INT_THREE = 3;
    public static final int INT_FIVE = 5;

    public static final int NUMBER_OF_CUISINES = 19;
    public static final int NUMBER_OF_RESTAURANTS = 200;
    public static final BigDecimal BIG_DECIMAL_FIFTY = new BigDecimal("50");

    public static final String RESTAURANT_DELICIOUSSCAPE = "Deliciousscape";
    public static final String CUISINE_AMERICAN = "American";

    public static SearchRestaurantInputBuilder createSearchRestaurantInputDefault() {
        return SearchRestaurantInput.builder()
                .restaurant(RESTAURANT_DELICIOUSSCAPE)
                .customerRating(INT_FIVE)
                .distance(INT_FIVE)
                .price(BIG_DECIMAL_FIFTY)
                .cuisine(CUISINE_AMERICAN);
    }

    public static SearchRestaurantOutputBuilder createSearchRestaurantOutputDefault() {
        return SearchRestaurantOutput.builder()
                .restaurant(RESTAURANT_DELICIOUSSCAPE)
                .customerRating(INT_FIVE)
                .distance(INT_FIVE)
                .price(BIG_DECIMAL_FIFTY)
                .cuisine(CUISINE_AMERICAN);
    }

    public static CuisineBuilder createAmericanCuisine() {
        return Cuisine.builder()
                .id(LONG_ONE)
                .name(CUISINE_AMERICAN);
    }

    public static RestaurantBuilder createExcellentRestaurant() {
        return Restaurant.builder()
                .name("Excellent")
                .customerRating(INT_FIVE)
                .distance(INT_FIVE)
                .price(TEN)
                .cuisine(createAmericanCuisine().build());
    }

    public static RestaurantBuilder createMediumRestaurant() {
        return Restaurant.builder()
                .name("Medium")
                .customerRating(INT_THREE)
                .distance(INT_FIVE)
                .price(TEN)
                .cuisine(createAmericanCuisine().build());
    }

    public static RestaurantBuilder createBadRestaurant() {
        return Restaurant.builder()
                .name("Bad")
                .customerRating(INT_ONE)
                .distance(INT_FIVE)
                .price(TEN)
                .cuisine(createAmericanCuisine().build());
    }
}