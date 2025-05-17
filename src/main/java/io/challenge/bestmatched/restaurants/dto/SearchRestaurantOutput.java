package io.challenge.bestmatched.restaurants.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SearchRestaurantOutput(String name, Integer customerRating, Integer distance, BigDecimal price, String cuisine) {
}
