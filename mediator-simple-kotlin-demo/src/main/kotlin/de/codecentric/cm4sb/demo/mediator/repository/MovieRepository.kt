package de.codecentric.cm4sb.demo.mediator.repository

import de.codecentric.cm4sb.demo.mediator.domain.Movie
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : CrudRepository<Movie, Long>
