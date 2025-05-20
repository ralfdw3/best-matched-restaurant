package io.challenge.bestmatched.restaurants.service.filters;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantInput;
import io.challenge.bestmatched.restaurants.model.Restaurant;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.challenge.bestmatched.restaurants.util.DataFactory.createSearchRestaurantInputDefault;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CuisineFilterTest {

    @Mock
    private Root<Restaurant> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder builder;

    @InjectMocks
    private CuisineFilter cuisineFilter;

    @Test
    void shouldCreateFilterPredicateWhenCuisineIsProvided() {
        mockCuisineExpression();

        var predicate = cuisineFilter.createFilterPredicate(root, query, builder, createSearchRestaurantInputDefault().build());

        assertThat(predicate).isNotNull();
    }

    @Test
    void shouldBeFilteredWhenCuisineIsProvided() {
        assertTrue(cuisineFilter.shouldBeFiltered(createSearchRestaurantInputDefault().build()));
    }

    @ParameterizedTest(name = "Should not filter when cuisine is {0}")
    @NullAndEmptySource
    void shouldNotBeFilteredWhenCuisineIsEmptyOrNull(final String cuisine) {
        final SearchRestaurantInput input = createSearchRestaurantInputDefault()
                .cuisine(cuisine)
                .build();

        assertFalse(cuisineFilter.shouldBeFiltered(input));
    }

    private void mockCuisineExpression() {
        final Expression<String> expression = mock(Expression.class);
        final Path<Object> cuisinePath = mock(Path.class);
        final Path<Object> namePath = mock(Path.class);

        when(root.get("cuisine")).thenReturn(cuisinePath);
        when(cuisinePath.get("name")).thenReturn(namePath);
        when(builder.lower(any(Expression.class))).thenReturn(expression);
        when(builder.like(any(Expression.class), anyString())).thenReturn(mock(Predicate.class));
    }
} 