package io.challenge.bestmatched.restaurants.service.filters;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantInput;
import io.challenge.bestmatched.restaurants.entity.Restaurant;
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
class PriceFilterTest {

    @Mock
    private Root<Restaurant> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder builder;

    @InjectMocks
    private PriceFilter priceFilter;

    @Test
    void shouldCreateFilterPredicateWhenPriceIsProvided() {
        mockPriceExpression();

        var predicate = priceFilter.createFilterPredicate(root, query, builder, createSearchRestaurantInputDefault().build());

        assertThat(predicate).isNotNull();
    }

    @Test
    void shouldBeFilteredWhenPriceIsProvided() {
        assertTrue(priceFilter.shouldBeFiltered(createSearchRestaurantInputDefault().build()));
    }

    @Test
    void shouldNotBeFilteredWhenPriceIsNull() {
        final SearchRestaurantInput input = createSearchRestaurantInputDefault()
                .price(null)
                .build();

        assertFalse(priceFilter.shouldBeFiltered(input));
    }

    private void mockPriceExpression() {
        final Path<Object> pricePath = mock(Path.class);
        when(root.get("price")).thenReturn(pricePath);
        when(builder.lessThanOrEqualTo(any(Expression.class), any(Comparable.class))).thenReturn(mock(Predicate.class));
    }
} 