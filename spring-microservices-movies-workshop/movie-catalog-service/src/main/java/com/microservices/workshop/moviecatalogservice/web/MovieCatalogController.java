package com.microservices.workshop.moviecatalogservice.web;

import com.microservices.workshop.moviecatalogservice.models.CatalogItem;
import com.microservices.workshop.moviecatalogservice.models.Movie;
import com.microservices.workshop.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;

    public MovieCatalogController(RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
    }

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        /**
         Steps:
         1. Get all rated movie Ids
         2. For each Id call movie info service and get det the details
         3. Put them all together
         **/
        List<Rating> ratings = Arrays.asList(
                new Rating("movie1", 2), new Rating("movie2", 5), new Rating("movie2", 5)
        );

        return ratings
                .stream()
                .map(r -> {
                    //Calling other Microservice by RestTemplate
//            Movie movie = restTemplate.
//            getForObject("http://localhost:8001/movie/"+ r.getMovieId(), Movie.class);

                    //Calling other Microservice by WebClient
                    Movie movie = webClientBuilder.build()
                            .get() //HTTP Method (GET, Post...)
                            .uri("http://localhost:8001/movie/" + r.getMovieId())
                            .retrieve()
                            .bodyToMono(Movie.class) //When data is come back convert it to Movie.class
                            .block();

                    return new CatalogItem(movie.getName(), "Descriptions", r.getRating());
                }).collect(Collectors.toList());
    }
}
