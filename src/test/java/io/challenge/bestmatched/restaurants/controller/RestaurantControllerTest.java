package io.challenge.bestmatched.restaurants.controller;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantOutput;
import io.challenge.bestmatched.restaurants.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static io.challenge.bestmatched.restaurants.util.DataFactory.createSearchRestaurantInputDefault;
import static io.challenge.bestmatched.restaurants.util.DataFactory.createSearchRestaurantOutputDefault;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    @Test
    void shouldReturnRestaurantsWhenSearchIsSuccessful() {
        final SearchRestaurantOutput expected = createSearchRestaurantOutputDefault().build();
        when(restaurantService.searchRestaurants(any())).thenReturn(List.of(expected));

        final List<SearchRestaurantOutput> result = restaurantController.searchRestaurants(createSearchRestaurantInputDefault().build());

        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isEqualTo(expected);
    }
}