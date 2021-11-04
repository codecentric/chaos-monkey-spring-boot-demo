package de.codecentric.cm4sb.demo.outgoing.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import de.codecentric.cm4sb.demo.outgoing.domain.Movie
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono


@Service
class HttpBinService(val client: WebClient) {

    val mapper = ObjectMapper()

    fun get(): JsonNode? {
        return getAsync().block()
    }
    
    fun getAsync(): Mono<JsonNode> {
        return client.get()
                .uri("/get")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono()
    }

    fun getMovieSuccessor(movie: Movie): Movie? {
        return getMovieSuccessorAsync(movie).block()
    }

    fun getMovieSuccessorAsync(movie: Movie): Mono<Movie>{
        val successorMovie = movie.copy(
                id = movie.id + 1337,
                title = movie.title + " 2",
        )
        return client
                .post()
                .uri("/post")
                .body(BodyInserters.fromValue(successorMovie))
                .retrieve()
                .bodyToMono<JsonNode>()
                .map { it.path("json") }
                .map { mapper.treeToValue(it, Movie::class.java) }
    }
}