package de.codecentric.cm4sb.demo.outgoing.endpoint;

import de.codecentric.cm4sb.demo.outgoing.domain.Movie;
import de.codecentric.cm4sb.demo.outgoing.endpoint.MovieController;
import de.codecentric.cm4sb.demo.outgoing.service.MovieService;
import de.codecentric.cm4sb.demo.outgoing.service.MovieSuccessorService;
import org.junit.jupiter.api.Test;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieControllerTest {

	private final MovieService movieService = mock(MovieService.class);
	private final MovieSuccessorService movieSuccessorService = mock(MovieSuccessorService.class);
	private final Movie movie = new Movie("a Movie", "1h");
	private final MockMvc restMock = MockMvcBuilders
			.standaloneSetup(new MovieController(movieService, movieSuccessorService))
			.build();


	@Test
	public void recommendationWithoutMoviesSuccessor() throws Exception {
		when(movieService.getRecommendedMovie()).thenReturn(movie);
		when(movieSuccessorService.getMovieSuccessor(movie)).thenReturn(null);

		restMock.perform(MockMvcRequestBuilders.get("/movies"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("{movie: {title: \"a Movie\", duration: \"1h\"}}"));
	}

	@Test
	public void recommendationWithMoviesSuccessor() throws Exception {
		when(movieService.getRecommendedMovie()).thenReturn(movie);
		when(movieSuccessorService.getMovieSuccessor(movie)).thenReturn(new Movie("a Movie - strikes back", "2h"));
		restMock.perform(MockMvcRequestBuilders.get("/movies"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(
						"""
									{
										"movie": {"title": "a Movie", "duration": "1h"},
										"successorMovie":{"title":"a Movie - strikes back","duration":"2h"}
									}
								"""
				));
	}
}
