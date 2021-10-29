package de.codecentric.cm4sb.demo.mediator.endpoint

import de.codecentric.cm4sb.demo.mediator.domain.Movie
import de.codecentric.cm4sb.demo.mediator.service.MovieService

import org.springframework.web.bind.annotation.GetMapping

import org.springframework.web.bind.annotation.RestController


@RestController
class MovieController(val movieService: MovieService) {

    @GetMapping("/movies")
    fun getRecommendation(): Movie = movieService.getMovie()
}
