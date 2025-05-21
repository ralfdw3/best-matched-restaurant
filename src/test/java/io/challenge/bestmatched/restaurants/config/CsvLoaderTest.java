package io.challenge.bestmatched.restaurants.config;

import io.challenge.bestmatched.restaurants.entity.Cuisine;
import io.challenge.bestmatched.restaurants.entity.Restaurant;
import io.challenge.bestmatched.restaurants.repository.CuisineRepository;
import io.challenge.bestmatched.restaurants.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static io.challenge.bestmatched.restaurants.util.DataFactory.NUMBER_OF_CUISINES;
import static io.challenge.bestmatched.restaurants.util.DataFactory.NUMBER_OF_RESTAURANTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CsvLoaderTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CuisineRepository cuisineRepository;

    @Captor
    private ArgumentCaptor<List<Cuisine>> cuisinesCaptor;

    @Captor
    private ArgumentCaptor<List<Restaurant>> restaurantsCaptor;

    private CsvLoader csvLoader;

    @BeforeEach
    void setUp() {
        csvLoader = new CsvLoader(restaurantRepository, cuisineRepository);
    }

    @Test
    void shouldLoadRepositoriesFromCsvFile() {
        when(cuisineRepository.findById(any())).thenReturn(Optional.of(new Cuisine()));

        csvLoader.loadDatabase();

        verify(cuisineRepository).saveAll(cuisinesCaptor.capture());
        verify(restaurantRepository).saveAll(restaurantsCaptor.capture());
        assertThat(cuisinesCaptor.getValue()).hasSize(NUMBER_OF_CUISINES);
        assertThat(restaurantsCaptor.getValue()).hasSize(NUMBER_OF_RESTAURANTS);
    }
}