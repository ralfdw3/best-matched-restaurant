package io.challenge.bestmatched.restaurants.service;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantInput;
import io.challenge.bestmatched.restaurants.dto.SearchRestaurantOutput;
import io.challenge.bestmatched.restaurants.model.Restaurant;
import io.challenge.bestmatched.restaurants.repository.RestaurantRepository;
import io.challenge.bestmatched.restaurants.service.filters.FilterStrategy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchRestaurantService {
    private static final int MAX_SIZE = 5;
    private final RestaurantRepository restaurantRepository;
    private final List<FilterStrategy> filterStrategies;

    public List<SearchRestaurantOutput> searchRestaurants(final SearchRestaurantInput input) {
        log.info("Searching restaurants for {}", input);
        return restaurantRepository.findAll(buildFilterSpecification(input))
                .stream()
                .sorted(buildOrderComparator())
                .limit(MAX_SIZE)
                .map(Restaurant::toOutput)
                .toList();
    }

    private Specification<Restaurant> buildFilterSpecification(final SearchRestaurantInput input) {
        return (root, query, builder) ->
                builder.and(buildFiltersPredicate(input, root, query, builder));
    }

    private Predicate[] buildFiltersPredicate(final SearchRestaurantInput input,
                                              final Root<Restaurant> root,
                                              final CriteriaQuery<?> query,
                                              final CriteriaBuilder builder) {
        return filterStrategies.stream()
                .filter(filter -> filter.shouldBeFiltered(input))
                .map(filter -> filter.createFilterPredicate(root, query, builder, input))
                .toArray(Predicate[]::new);
    }

    private static Comparator<Restaurant> buildOrderComparator() {
        return comparing(Restaurant::getDistance)
                .thenComparing(Restaurant::getCustomerRating, reverseOrder())
                .thenComparing(Restaurant::getPrice)
                .thenComparing(getRandomRestaurant());
    }

    private static Function<Restaurant, UUID> getRandomRestaurant() {
        return restaurant -> UUID.randomUUID();
    }
}
