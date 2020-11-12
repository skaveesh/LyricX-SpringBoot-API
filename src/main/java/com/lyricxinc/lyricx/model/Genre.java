package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Genre {

    @Id
    @SequenceGenerator(name = "genre_id_seq", sequenceName = "genre_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_id_seq")
    private Short id;

    @NotBlank
    @Size(max = 30)
    private String genreName;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime addedDate;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastModifiedDate;

    public Genre() {

    }

    public Genre(@NotBlank String genreName) {

        this.genreName = genreName;
    }

    public Short getId() {

        return id;
    }

    public void setId(Short id) {

        this.id = id;
    }

    public String getGenreName() {

        return genreName;
    }

    public void setGenreName(String genreName) {

        this.genreName = genreName;
    }

    public LocalDateTime getAddedDate() {

        return addedDate;
    }

    public LocalDateTime getLastModifiedDate() {

        return lastModifiedDate;
    }

}
