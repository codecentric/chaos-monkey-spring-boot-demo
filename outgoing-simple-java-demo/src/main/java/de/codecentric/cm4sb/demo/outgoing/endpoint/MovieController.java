package de.codecentric.cm4sb.demo.outgoing.endpoint;

import de.codecentric.cm4sb.demo.outgoing.domain.Movie;
import de.codecentric.cm4sb.demo.outgoing.domain.MovieWithSuccessor;
import de.codecentric.cm4sb.demo.outgoing.service.MovieService;
import de.codecentric.cm4sb.demo.outgoing.service.MovieSuccessorService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

  private final MovieService movieService;
  private final MovieSuccessorService movieSuccessorService;

  public MovieController(
          MovieService movieService,
          MovieSuccessorService movieSuccessorService){
    this.movieService = movieService;
    this.movieSuccessorService = movieSuccessorService;
  }

  @GetMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
  public MovieWithSuccessor getRecommendation(@RequestParam(required = false) String title){
    Movie movie = getRandomMovieOrByTitle(title);
    System.out.println("Recommend movie: $movie");
    var movieSuccessor = movieSuccessorService.getMovieSuccessor(movie);
    return new MovieWithSuccessor(movie, movieSuccessor);
  }

  private Movie getRandomMovieOrByTitle(String title) {
    Movie movie;
    if (title == null) {
      movie = movieService.getRecommendedMovie();
    } else {
      movie = movieService.getMovieByTitle(title);
    }
    return movie;
  }
}
