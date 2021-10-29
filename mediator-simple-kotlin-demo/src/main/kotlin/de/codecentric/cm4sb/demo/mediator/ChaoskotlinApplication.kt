package de.codecentric.cm4sb.demo.mediator

import de.codecentric.cm4sb.demo.mediator.domain.Movie
import de.codecentric.cm4sb.demo.mediator.repository.MovieRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ChaoskotlinApplication{

    @Bean
    fun demo(repository: MovieRepository): CommandLineRunner {
        return CommandLineRunner {
            repository.save(Movie("Inception", "2h 28m"))
            repository.save(Movie("Source Code", "1h 33m"))
            repository.save(Movie("Forest Gump", "2h 22m"))
            repository.save(Movie("Life Is Beautiful", "1h 56m"))
            repository.save(Movie("12 Angry Men", "1h 36min"))
            repository.save(Movie("Spider-Man: A New Universe", "1h 56m"))
            repository.save(Movie("Swiss Army Man", "1h 37m"))
            repository.save(Movie("John Wick 3", "2h 10m"))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ChaoskotlinApplication>(*args)
}



