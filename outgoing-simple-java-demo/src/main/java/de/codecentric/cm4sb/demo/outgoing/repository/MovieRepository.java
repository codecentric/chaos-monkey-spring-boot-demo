package de.codecentric.cm4sb.demo.outgoing.repository;

import de.codecentric.cm4sb.demo.outgoing.domain.Movie;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
	Movie getByTitle(String title);
}
