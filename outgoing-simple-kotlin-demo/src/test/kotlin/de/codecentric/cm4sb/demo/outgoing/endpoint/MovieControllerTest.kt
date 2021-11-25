package de.codecentric.cm4sb.demo.outgoing.endpoint

import de.codecentric.cm4sb.demo.outgoing.domain.Movie
import de.codecentric.cm4sb.demo.outgoing.service.MovieService
import de.codecentric.cm4sb.demo.outgoing.service.MovieSuccessorService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class MovieControllerTest{
    private val movieService: MovieService = Mockito.mock(MovieService::class.java)
    private val movieSuccessorService: MovieSuccessorService = Mockito.mock(MovieSuccessorService::class.java)
    private val restMock = MockMvcBuilders.standaloneSetup(MovieController(movieSuccessorService, movieService)).build()
    private val movie = Movie("a Movie", "1h")

    @Test
    @Throws(Exception::class)
    fun recommendationWithoutMoviesSuccessor() {
        `when`(movieService.getRecommendedMovie()).thenReturn(movie)
        `when`(movieSuccessorService.getMovieSuccessor(movie)).thenReturn(null)

        restMock.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().json("{movie: {title: \"a Movie\", duration: \"1h\"}}"))
    }

    @Test
    @Throws(Exception::class)
    fun recommendationWithMoviesSuccessor() {
        `when`(movieService.getRecommendedMovie()).thenReturn(movie)
        `when`(movieSuccessorService.getMovieSuccessor(movie)).thenReturn(Movie("a Movie - strikes back", "2h"))
        restMock.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().json(
                """
                    {
                        "movie": {"title": "a Movie", "duration": "1h"},
                        "successorMovie":{"title":"a Movie - strikes back","duration":"2h"}
                    }
                """.trimIndent()
                ))
    }
}
