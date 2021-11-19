package de.codecentric.cm4sb.demo.outgoing.service;

import de.codecentric.cm4sb.demo.outgoing.domain.Movie;
import de.codecentric.cm4sb.demo.outgoing.repository.MovieRepository;
import de.codecentric.cm4sb.demo.outgoing.service.MovieService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MovieServiceTest {

    private MovieRepository movieRepository = mock(MovieRepository.class);
    private MovieService movieService = new MovieService(movieRepository);


    @Test
    public void recommendMovieWhenMoviesAreAvailable(){
        List<Movie> movies = Arrays.asList(new Movie("some movie", "1h"), new Movie("some other movie", "2h"));
        when(movieRepository.findAll()).thenReturn(movies);
        assertThat(movieService.getMovie()).isIn(movies);
    }
}
