package MoviesMicroService.MovieRatingService.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MoviesMicroService.MovieRatingService.models.Rating;
import MoviesMicroService.MovieRatingService.models.UserRatings;

@RestController
@RequestMapping("/ratings")
public class RatingResource {
  @RequestMapping("/{movieId}")
  public Rating getMovieRating(@PathVariable("movieId") String movieId) {
	  return new Rating(movieId, 4);
  }
  
  @RequestMapping("/users/{userId}")
  public UserRatings getUserRating(@PathVariable("userId") String userId) {
	   List<Rating> ratings = Arrays.asList(
	     new Rating("123", 1),
         new Rating("12345", 1)
       );
	   	   
	   return new UserRatings(ratings);
  }
}
