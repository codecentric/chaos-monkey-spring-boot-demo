package de.codecentric.cm4sb.demo.outgoing.repository

import de.codecentric.cm4sb.demo.outgoing.domain.Movie
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : CrudRepository<Movie, Long> {
   fun getByTitle(title: String): Movie
}
