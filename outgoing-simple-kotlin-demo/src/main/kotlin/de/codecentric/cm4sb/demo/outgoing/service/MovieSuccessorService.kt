package de.codecentric.cm4sb.demo.outgoing.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import de.codecentric.cm4sb.demo.outgoing.domain.Movie
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono


@Service
class MovieSuccessorService(val client: WebClient) {

    val mapper = ObjectMapper()

    fun getMovieSuccessor(movie: Movie): Movie? {
        return runBlocking {
            withTimeout(2000) {
                getMovieSuccessorSuspending(movie)
            }
        }
    }

    suspend fun getMovieSuccessorSuspending(movie: Movie): Movie {
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
                .awaitSingle()
    }
}