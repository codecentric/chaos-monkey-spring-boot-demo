package de.codecentric.cm4sb.demo.outgoing.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String duration;

    public Movie(String title, String duration) {
        this.title = title;
        this.duration = duration;
    }
}
