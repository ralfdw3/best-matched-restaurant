package io.challenge.bestmatched.restaurants.controller;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantInput;
import io.challenge.bestmatched.restaurants.dto.SearchRestaurantOutput;
import io.challenge.bestmatched.restaurants.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/search/")
@RequiredArgsConstructor
public class SearchRestaurantController implements SearchRestaurantControllerDocumentation{
    private final RestaurantService restaurantService;

    @PostMapping("/restaurants")
    public List<SearchRestaurantOutput> searchRestaurants(@RequestBody @Valid final SearchRestaurantInput input) {
        return restaurantService.searchRestaurants(input);
    }
}
