package io.challenge.bestmatched.restaurants.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SearchRestaurantOutput(String restaurant, Integer customerRating, Integer distance, BigDecimal price, String cuisine) {
}
