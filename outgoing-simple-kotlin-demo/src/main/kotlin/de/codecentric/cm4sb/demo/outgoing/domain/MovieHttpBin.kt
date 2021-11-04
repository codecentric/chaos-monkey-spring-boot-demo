package de.codecentric.cm4sb.demo.outgoing.domain

import com.fasterxml.jackson.databind.JsonNode

data class MovieHttpBin(val movie: Movie, val httpBin: JsonNode?, val successorMovie: Movie?)