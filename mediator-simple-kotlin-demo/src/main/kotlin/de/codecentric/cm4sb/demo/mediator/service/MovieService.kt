package de.codecentric.cm4sb.demo.mediator.service

import de.codecentric.cm4sb.demo.mediator.domain.Movie
import de.codecentric.cm4sb.demo.mediator.repository.MovieRepository
import io.vavr.control.Try
import org.springframework.stereotype.Service
import java.util.*

@Service
class MovieService(val movieRepository: MovieRepository) {
    private val fallbackMovie: Movie = Movie("Titanic", "3h 15m")

    fun getMovies(): List<Movie> = movieRepository.findAll().toList()

    fun getMovie(): Movie = Try.of(this::getRecommendedMovie).getOrElse(fallbackMovie)

    fun getRecommendedMovie(): Movie {
        val movies = getMovies()
        return movies[Random().nextInt(movies.size)]
    }

}
