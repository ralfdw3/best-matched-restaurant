package io.challenge.bestmatched.restaurants.controller;

import io.challenge.bestmatched.restaurants.dto.SearchRestaurantInput;
import io.challenge.bestmatched.restaurants.dto.SearchRestaurantOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RestaurantControllerDocumentation {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema =
                    @Schema(implementation = SearchRestaurantOutput.class)))
            }),
            @ApiResponse(responseCode = "4xx, 5xx", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema =
                    @Schema(implementation = ProblemDetail.class)))
            })
    })
    @Operation(summary = "Search for the perfect restaurant for you")
    List<SearchRestaurantOutput> searchRestaurants(@RequestBody @Valid final SearchRestaurantInput input);
}
