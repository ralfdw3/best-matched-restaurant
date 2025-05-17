package io.challenge.bestmatched.restaurants.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SearchRestaurantInput(String restaurant,
                                    @Min(value = 1, message = "Customer rating must be at least 1")
                                    @Max(value = 5, message = "Customer rating must be at most 5")
                                    Integer customerRating,
                                    @Min(value = 1, message = "Distance must be at least 1")
                                    @Max(value = 10, message = "Distance must be at most 10")
                                    Integer distance,
                                    @Min(value = 1, message = "Price must be at least 1")
                                    @Max(value = 50, message = "Price must be at most 50")
                                    BigDecimal price,
                                    String cuisine) {
}
