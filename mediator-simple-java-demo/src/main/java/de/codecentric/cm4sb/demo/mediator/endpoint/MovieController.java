package de.codecentric.cm4sb.demo.mediator.endpoint;

import de.codecentric.cm4sb.demo.mediator.domain.Movie;
import de.codecentric.cm4sb.demo.mediator.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

  private final MovieService movieService;

  public MovieController(MovieService movieService){
    this.movieService = movieService;
  }

  @GetMapping("/movies")
  public Movie getRecommendation() {
    return movieService.getMovie();
  }
}
