package de.codecentric.cm4sb.demo.outgoing.endpoint

import de.codecentric.cm4sb.demo.outgoing.domain.Movie
import de.codecentric.cm4sb.demo.outgoing.domain.MovieWithSuccessor
import de.codecentric.cm4sb.demo.outgoing.service.MovieSuccessorService
import de.codecentric.cm4sb.demo.outgoing.service.MovieService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class MovieController(val movieSuccessorService: MovieSuccessorService, val movieService: MovieService) {

    @GetMapping("/movies", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getRecommendation(@RequestParam(required = false) title: String?): MovieWithSuccessor {
        val movie = getRandomMovieOrByTitle(title)

        val movieSuccessor = movieSuccessorService.getMovieSuccessor(movie)
        return MovieWithSuccessor(movie, movieSuccessor)
    }

    private fun getRandomMovieOrByTitle(title: String?): Movie {
        val movie = if (title == null) {
            movieService.getRecommendedMovie()
        } else {
            movieService.getMovieByTitle(title)
        }
        return movie
    }
}
