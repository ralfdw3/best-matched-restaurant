package io.challenge.bestmatched.restaurants.controller;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantInput;
import io.challenge.bestmatched.restaurants.dto.SearchRestaurantOutput;
import io.challenge.bestmatched.restaurants.service.SearchRestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/search/")
@RequiredArgsConstructor
public class SearchRestaurantController {
    private final SearchRestaurantService searchRestaurantService;

    @GetMapping("/restaurants")
    public List<SearchRestaurantOutput> searchRestaurants(@RequestBody @Valid final SearchRestaurantInput input) {
        return searchRestaurantService.searchRestaurants(input);
    }
}
