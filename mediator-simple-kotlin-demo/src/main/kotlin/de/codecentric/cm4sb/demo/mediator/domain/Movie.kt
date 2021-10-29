package de.codecentric.cm4sb.demo.mediator.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Movie(
        val title: String,
        val duration: String,
        @Id @GeneratedValue
        var id: Long = -1,)
