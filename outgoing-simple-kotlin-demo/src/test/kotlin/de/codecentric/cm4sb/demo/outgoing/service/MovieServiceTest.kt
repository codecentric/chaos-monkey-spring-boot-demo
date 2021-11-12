package de.codecentric.cm4sb.demo.outgoing.service

import de.codecentric.cm4sb.demo.outgoing.domain.Movie
import de.codecentric.cm4sb.demo.outgoing.repository.MovieRepository
import de.codecentric.cm4sb.demo.outgoing.service.MovieService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

internal class MovieServiceTest{
    private val movieRepository: MovieRepository = mock(MovieRepository::class.java)
    private val movieService = MovieService(movieRepository)


    @Test
    fun recommendMovieWhenMoviesAreAvailable() {
        val movies = listOf(Movie("some movie", "1h"), Movie("some other movie", "2h"))
        `when`(movieRepository.findAll()).thenReturn(movies)
        assertThat(movieService.getMovie()).isIn(movies)
    }
}
