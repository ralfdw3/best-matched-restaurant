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
class DistanceFilterTest {

    @Mock
    private Root<Restaurant> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder builder;

    @InjectMocks
    private DistanceFilter distanceFilter;

    @Test
    void shouldCreateFilterPredicateWhenDistanceIsProvided() {
        mockDistanceExpression();

        var predicate = distanceFilter.createFilterPredicate(root, query, builder, createSearchRestaurantInputDefault().build());

        assertThat(predicate).isNotNull();
    }

    @Test
    void shouldBeFilteredWhenDistanceIsProvided() {
        assertTrue(distanceFilter.shouldBeFiltered(createSearchRestaurantInputDefault().build()));
    }

    @Test
    void shouldNotBeFilteredWhenDistanceIsNull() {
        SearchRestaurantInput input = createSearchRestaurantInputDefault()
                .distance(null)
                .build();

        assertFalse(distanceFilter.shouldBeFiltered(input));
    }

    private void mockDistanceExpression() {
        final Path<Object> distancePath = mock(Path.class);
        when(root.get("distance")).thenReturn(distancePath);
        when(builder.lessThanOrEqualTo(any(Expression.class), any(Comparable.class))).thenReturn(mock(Predicate.class));
    }
} 