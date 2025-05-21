package io.challenge.bestmatched.restaurants.entity;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantOutput;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer customerRating;
    private Integer distance;
    private BigDecimal price;
    @ManyToOne
    private Cuisine cuisine;

    public SearchRestaurantOutput toOutput() {
        return SearchRestaurantOutput.builder()
                .restaurant(this.name)
                .customerRating(this.customerRating)
                .distance(this.distance)
                .price(this.price)
                .cuisine(this.cuisine.getName())
                .build();
    }
}
