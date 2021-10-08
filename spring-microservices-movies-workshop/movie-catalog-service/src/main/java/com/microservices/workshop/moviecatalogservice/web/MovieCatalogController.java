package com.microservices.workshop.moviecatalogservice.web;

import com.microservices.workshop.moviecatalogservice.models.CatalogItem;
import com.microservices.workshop.moviecatalogservice.models.Movie;
import com.microservices.workshop.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {



    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        /**
         Steps:
         1. Get all rated movie Ids
         2. For each Id call movie info service and get det the details
         3. Put them all together
         **/
        RestTemplate restTemplate = new RestTemplate();

        List<Rating> ratings = Arrays.asList(
          new Rating("movie1",2), new Rating("movie2",5), new Rating("movie2",5)
        );

        return ratings
                .stream()
                .map(r -> {
            Movie movie = restTemplate.getForObject("http://localhost:8001/movie/"+ r.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), "Descriptions", r.getRating());
        }).collect(Collectors.toList());
    }
}
