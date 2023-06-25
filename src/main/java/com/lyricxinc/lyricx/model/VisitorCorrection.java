package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class VisitorCorrection {

    @Id
    @SequenceGenerator(name = "visitor_correction_id_seq", sequenceName = "visitor_correction_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitor_correction_id_seq")
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "songId", nullable = false)
    @JsonBackReference(value = "ReferenceSong")
    private Song song;

    @NotBlank
    private String submitterName;

    @NotBlank
    private String submitterEmail;

    @NotBlank
    private String correction;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime addedDate;

    public VisitorCorrection() {

    }

    public VisitorCorrection(Song song, @NotBlank String submitterName, @NotBlank String submitterEmail, @NotBlank String correction) {

        this.song = song;
        this.submitterName = submitterName;
        this.submitterEmail = submitterEmail;
        this.correction = correction;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Song getSong() {

        return song;
    }

    public void setSong(Song song) {

        this.song = song;
    }

    public String getSubmitterName() {

        return submitterName;
    }

    public void setSubmitterName(String submitterName) {

        this.submitterName = submitterName;
    }

    public String getSubmitterEmail() {

        return submitterEmail;
    }

    public void setSubmitterEmail(String submitterEmail) {

        this.submitterEmail = submitterEmail;
    }

    public String getCorrection() {

        return correction;
    }

    public void setCorrection(String correction) {

        this.correction = correction;
    }

    public LocalDateTime getAddedDate() {

        return addedDate;
    }

}
