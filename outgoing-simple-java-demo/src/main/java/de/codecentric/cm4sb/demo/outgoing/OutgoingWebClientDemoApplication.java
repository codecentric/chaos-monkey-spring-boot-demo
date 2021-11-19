package de.codecentric.cm4sb.demo.outgoing;

import de.codecentric.cm4sb.demo.outgoing.domain.Movie;
import de.codecentric.cm4sb.demo.outgoing.repository.MovieRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OutgoingWebClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutgoingWebClientDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(MovieRepository repository) {
		return (args) -> {
			repository.save(new Movie("Inception", "2h 28m"));
			repository.save(new Movie("Source Code", "1h 33m"));
			repository.save(new Movie("Forest Gump", "2h 22m"));
			repository.save(new Movie("Life Is Beautiful", "1h 56m"));
			repository.save(new Movie("12 Angry Men", "1h 36min"));
			repository.save(new Movie("Spider-Man: A New Universe", "1h 56m"));
			repository.save(new Movie("Swiss Army Man", "1h 37m"));
			repository.save(new Movie("John Wick 3", "2h 10m"));
		};
	}
}
