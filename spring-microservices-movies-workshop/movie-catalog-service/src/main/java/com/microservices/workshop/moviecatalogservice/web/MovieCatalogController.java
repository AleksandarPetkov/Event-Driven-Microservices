package com.microservices.workshop.moviecatalogservice.web;

import com.microservices.workshop.moviecatalogservice.models.CatalogItem;
import com.microservices.workshop.moviecatalogservice.models.Movie;
import com.microservices.workshop.moviecatalogservice.models.Rating;
import com.microservices.workshop.moviecatalogservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    private final RestTemplate restTemplate;
//    private final WebClient.Builder webClientBuilder;

    public MovieCatalogController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        /**
         Steps:
         1. Get all rated movie Ids
         2. For each Id call movie info service and get det the details
         3. Put them all together
         **/

        UserRating userRating = restTemplate
                // rating-data-service we take this from eureka and is declared in application.properties
                .getForObject("http://rating-data-service/ratingsdata/user/" + userId, UserRating.class);

        return userRating.getRatings()
                .stream()
                .map(r -> {
                    //Calling other Microservice by RestTemplate
            Movie movie = restTemplate.
            getForObject("http://movie-info-service/movie/"+ r.getMovieId(), Movie.class);

                    //Calling other Microservice by WebClient
//                    Movie movie = webClientBuilder.build()
//                            .get() //HTTP Method (GET, Post...)
//                            .uri("http://localhost:8001/movie/" + r.getMovieId())
//                            .retrieve()
//                            .bodyToMono(Movie.class) //When data is come back convert it to Movie.class
//                            .block();

                    return new CatalogItem(movie.getName(), "Descriptions", r.getRating());
                }).collect(Collectors.toList());
    }
}
