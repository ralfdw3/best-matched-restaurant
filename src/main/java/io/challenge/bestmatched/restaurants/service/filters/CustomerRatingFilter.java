package io.challenge.bestmatched.restaurants.service.filters;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantInput;
import io.challenge.bestmatched.restaurants.model.Restaurant;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class CustomerRatingFilter implements FilterStrategy {
    @Override
    public Predicate createFilterPredicate(final Root<Restaurant> root,
                                           final CriteriaQuery<?> query,
                                           final CriteriaBuilder builder,
                                           final SearchRestaurantInput input) {
        return builder.greaterThanOrEqualTo(root.get("customerRating"), input.customerRating());
    }

    @Override
    public boolean shouldBeFiltered(final SearchRestaurantInput input) {
        return nonNull(input.customerRating());
    }
}
