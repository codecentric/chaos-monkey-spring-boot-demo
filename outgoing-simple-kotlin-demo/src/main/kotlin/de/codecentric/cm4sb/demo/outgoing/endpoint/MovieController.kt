package de.codecentric.cm4sb.demo.outgoing.endpoint

import de.codecentric.cm4sb.demo.outgoing.domain.MovieHttpBin
import de.codecentric.cm4sb.demo.outgoing.service.HttpBinService
import de.codecentric.cm4sb.demo.outgoing.service.MovieService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class MovieController(val httpBinService: HttpBinService, val movieService: MovieService) {

    @GetMapping("/movies", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getRecommendation(@RequestParam(required = false) title: String?): MovieHttpBin {
        val movie = if (title == null) {
            movieService.getRecommendedMovie()
        } else {
            movieService.getMovieByTitle(title)
        }

        val httpBin = httpBinService.get()
        val movieSuccessor = httpBinService.getMovieSuccessor(movie)
        return MovieHttpBin(movie, httpBin, movieSuccessor)
    }

    @GetMapping("/movies/asynch", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getRecommendationAsync(@RequestParam(required = false) title: String?): Mono<MovieHttpBin> {
        val movie = if (title == null) {
            movieService.getRecommendedMovie()
        } else {
            movieService.getMovieByTitle(title)
        }

        val httpBin = httpBinService.getAsync()
        val movieSuccessor = httpBinService.getMovieSuccessorAsync(movie)
        return Mono
                .zip(httpBin, movieSuccessor)
                .map { MovieHttpBin(movie, it.t1, it.t2) }
    }
}
