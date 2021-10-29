package de.codecentric.cm4sb.demo.mediator.service;

import de.codecentric.cm4sb.demo.mediator.domain.Movie;
import de.codecentric.cm4sb.demo.mediator.repository.MovieRepository;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final Movie fallbackMovie = new Movie("Titanic", "3h 15m");

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovie() {
        return Try.of(this::getRecommendedMovie).getOrElse(fallbackMovie);
    }

    private List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }

    private Movie getRecommendedMovie() {
        List<Movie> movies = getMovies();
        return movies.get(new Random().nextInt(movies.size()));
    }
}
