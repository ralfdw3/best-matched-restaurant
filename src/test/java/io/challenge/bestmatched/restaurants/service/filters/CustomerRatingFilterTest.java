package io.challenge.bestmatched.restaurants.service.filters;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantInput;
import io.challenge.bestmatched.restaurants.model.Restaurant;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.challenge.bestmatched.restaurants.util.DataFactory.createSearchRestaurantInputDefault;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerRatingFilterTest {

    @Mock
    private Root<Restaurant> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder builder;

    @InjectMocks
    private CustomerRatingFilter customerRatingFilter;

    @Test
    void shouldCreateFilterPredicateWhenCustomerRatingIsProvided() {
        mockCustomerRatingExpression();

        var predicate = customerRatingFilter.createFilterPredicate(root, query, builder, createSearchRestaurantInputDefault().build());

        assertThat(predicate).isNotNull();
    }

    @Test
    void shouldBeFilteredWhenCustomerRatingIsProvided() {
        assertTrue(customerRatingFilter.shouldBeFiltered(createSearchRestaurantInputDefault().build()));
    }

    @Test
    void shouldNotBeFilteredWhenCustomerRatingIsNull() {
        final SearchRestaurantInput input = createSearchRestaurantInputDefault()
                .customerRating(null)
                .build();

        assertFalse(customerRatingFilter.shouldBeFiltered(input));
    }

    private void mockCustomerRatingExpression() {
        final Path<Object> customerRatingPath = mock(Path.class);
        when(root.get("customerRating")).thenReturn(customerRatingPath);
        when(builder.greaterThanOrEqualTo(any(Expression.class), any(Comparable.class))).thenReturn(mock(Predicate.class));
    }
} 