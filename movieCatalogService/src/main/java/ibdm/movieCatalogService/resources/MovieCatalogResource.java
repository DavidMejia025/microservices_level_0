package ibdm.movieCatalogService.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ibdm.movieCatalogService.models.CatalogItem;
import ibdm.movieCatalogService.models.Movie;
import ibdm.movieCatalogService.models.Rating;
import ibdm.movieCatalogService.models.UserRatings;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
  @Autowired
  private RestTemplate restTemplate;
  
  @RequestMapping("/{userId}")
  public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
	//CatalogItem item = new CatalogItem(name: "Transformers", desc: "Test", rating: 4);;
	
	UserRatings ratings = restTemplate.getForObject(
	  "http://localhost:8083/ratings/users/" + userId,
	  UserRatings.class
	);

    List<CatalogItem> result = ratings.getUserRatings().stream().map(rating -> {
      // for each movie ID call movi info service and get details
      Movie movie = restTemplate.getForObject(
		 "http://localhost:8082/movies/1" + rating.getMovieId(),
		 Movie.class);
      
	  return new CatalogItem(movie.getName(), "Test", rating.getRating());  
	})
    .collect(Collectors.toList());
	
	 return result;
	//
	
	// put them all together.
  }
}
