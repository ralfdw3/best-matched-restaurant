package io.challenge.bestmatched.restaurants.service;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantInput;
import io.challenge.bestmatched.restaurants.dto.SearchRestaurantOutput;
import io.challenge.bestmatched.restaurants.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static io.challenge.bestmatched.restaurants.util.DataFactory.*;
import static java.math.BigDecimal.*;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        restaurantService = new RestaurantService(restaurantRepository, emptyList());
    }

    @Test
    void shouldReturn3RestaurantsOrderedByDistance() {
        when(restaurantRepository.findAll(any(Specification.class)))
                .thenReturn(List.of(
                        createExcellentRestaurant()
                                .distance(10)
                                .build(),
                        createMediumRestaurant()
                                .distance(5)
                                .build(),
                        createBadRestaurant()
                                .distance(1)
                                .build()
                ));

        final List<SearchRestaurantOutput> result = restaurantService.searchRestaurants(createSearchRestaurantInputDefault().build());

        assertThat(result).hasSize(3);
        assertThat(result.get(0).distance()).isEqualTo(1);
        assertThat(result.get(1).distance()).isEqualTo(5);
        assertThat(result.get(2).distance()).isEqualTo(10);
    }

    @Test
    void shouldReturn3RestaurantsOrderedByCustomerRating() {
        when(restaurantRepository.findAll(any(Specification.class)))
                .thenReturn(List.of(
                        createBadRestaurant().build(),
                        createExcellentRestaurant().build(),
                        createMediumRestaurant().build()
                ));

        final List<SearchRestaurantOutput> result = restaurantService.searchRestaurants(createSearchRestaurantInputDefault().build());

        assertThat(result).hasSize(3);
        assertThat(result.get(0).restaurant()).isEqualTo("Excellent");
        assertThat(result.get(1).restaurant()).isEqualTo("Medium");
        assertThat(result.get(2).restaurant()).isEqualTo("Bad");
    }

    @Test
    void shouldReturn3RestaurantsOrderedByLowestPrice() {
        when(restaurantRepository.findAll(any(Specification.class)))
                .thenReturn(List.of(
                        createMediumRestaurant()
                                .price(ONE)
                                .build(),
                        createMediumRestaurant()
                                .price(TEN)
                                .build(),
                        createMediumRestaurant()
                                .price(TWO)
                                .build()
                ));

        final List<SearchRestaurantOutput> result = restaurantService.searchRestaurants(createSearchRestaurantInputDefault().build());

        assertThat(result).hasSize(3);
        assertThat(result.get(0).price()).isEqualTo(ONE);
        assertThat(result.get(1).price()).isEqualTo(TWO);
        assertThat(result.get(2).price()).isEqualTo(TEN);
    }

    @Test
    void shouldReturnEmptyListWhenNoRestaurantsFound() {
        when(restaurantRepository.findAll(any(Specification.class)))
                .thenReturn(emptyList());

        final SearchRestaurantInput input = SearchRestaurantInput.builder()
                .restaurant("NonExistent")
                .build();

        final List<SearchRestaurantOutput> result = restaurantService.searchRestaurants(input);

        assertThat(result).isEmpty();
    }

    @Test
    void shouldLimitResultsTo5Restaurants() {
        when(restaurantRepository.findAll(any(Specification.class)))
                .thenReturn(List.of(
                        createExcellentRestaurant().build(),
                        createExcellentRestaurant().build(),
                        createExcellentRestaurant().build(),
                        createBadRestaurant().build(),
                        createBadRestaurant().build(),
                        createBadRestaurant().build(),
                        createBadRestaurant().build()
                ));

        final List<SearchRestaurantOutput> result = restaurantService.searchRestaurants(createSearchRestaurantInputDefault().build());

        assertThat(result).hasSize(5);
    }
}