package io.challenge.bestmatched.restaurants.service.filterstrategy;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantInput;
import io.challenge.bestmatched.restaurants.model.Restaurant;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface FilterStrategy {
    Predicate createFilterPredicate(final Root<Restaurant> root,
                                    final CriteriaQuery<?> query,
                                    final CriteriaBuilder criteriaBuilder,
                                    final SearchRestaurantInput input);
    boolean shouldBeFiltered(final SearchRestaurantInput input);
}
