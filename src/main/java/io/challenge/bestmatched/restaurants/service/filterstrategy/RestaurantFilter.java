package io.challenge.bestmatched.restaurants.service.filterstrategy;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantInput;
import io.challenge.bestmatched.restaurants.model.Restaurant;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import static ch.qos.logback.core.util.StringUtil.notNullNorEmpty;

@Component
public class RestaurantFilter implements FilterStrategy {
    @Override
    public Predicate createFilterPredicate(final Root<Restaurant> root,
                                           final CriteriaQuery<?> query,
                                           final CriteriaBuilder builder,
                                           final SearchRestaurantInput input) {
        final Expression<String> expression = builder.lower(root.get("name"));
        final String pattern = "%" + input.restaurant().toLowerCase() + "%";

        return builder.like(expression, pattern);
    }

    @Override
    public boolean shouldBeFiltered(final SearchRestaurantInput input) {
        return notNullNorEmpty(input.restaurant());
    }
}
