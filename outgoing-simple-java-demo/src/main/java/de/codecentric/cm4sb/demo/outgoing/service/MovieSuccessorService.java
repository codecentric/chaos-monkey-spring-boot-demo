package de.codecentric.cm4sb.demo.outgoing.service;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.codecentric.cm4sb.demo.outgoing.domain.Movie;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class MovieSuccessorService {

    private final ObjectMapper mapper;
    private final WebClient client;

    public MovieSuccessorService(WebClient client){
        this.client = client;
        this.mapper = new ObjectMapper();
    }

    public Movie getMovieSuccessor(Movie movie) {
        var successorMovie = new Movie(movie.getId() + 1337,
                movie.getTitle() + " 2", movie.getDuration());
        return client
                .post()
                .uri("/post")
                .body(BodyInserters.fromValue(successorMovie))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonNode -> jsonNode.path("json"))
                .map(this::convertJsonToMovie)
                .block();
    }

    public Movie convertJsonToMovie(JsonNode movieAsJson){
        try {
            return mapper.treeToValue(movieAsJson, Movie.class);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


}