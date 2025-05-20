package io.challenge.bestmatched.restaurants.functionaltest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.challenge.bestmatched.restaurants.util.DataFactory.CUISINE_AMERICAN;
import static io.challenge.bestmatched.restaurants.util.DataFactory.INT_THREE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class SearchRestaurantFunctionalTest {

    private static final String URL_PATH = "/v1/search/restaurants";
    private static final String DETAIL = "detail";

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void shouldReturnRestaurantsWhenValidInputIsProvided() {
        final String requestBody = """
                {
                    "restaurant": "Deliciousscape",
                    "customerRating": 3,
                    "distance": 7,
                    "price": 50,
                    "cuisine": "American"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL_PATH)
                .then()
                .statusCode(200)
                .body("[0].restaurant", equalTo("Deliciousscape"))
                .body("[0].customerRating", equalTo(INT_THREE))
                .body("[0].distance", equalTo(7))
                .body("[0].price", equalTo(50F))
                .body("[0].cuisine", equalTo(CUISINE_AMERICAN));
    }

    @ParameterizedTest
    @ValueSource(strings = {"customerRating", "distance", "price"})
    void shouldReturnBadRequestWhenCustomerRatingIsString(String parameter) {
        String requestBody = """
                {
                    "%s": "shouldntBeAStringHere"
                }
                """.formatted(parameter);

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL_PATH)
                .then()
                .statusCode(400)
                .body(DETAIL, containsString("The request body is invalid or malformed."));
    }

    @Test
    void shouldReturnBadRequestWhenPriceIsNegative() {
        String requestBody = """
                {
                    "price": -30
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL_PATH)
                .then()
                .statusCode(400)
                .body(DETAIL, containsString("Price must be at least 1"));
    }

    @Test
    void shouldReturnEmptyListWhenNoRestaurantsMatch() {
        String requestBody = """
                {
                    "restaurant": "NonExistentRestaurant"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL_PATH)
                .then()
                .statusCode(200)
                .body("$", empty());
    }
}