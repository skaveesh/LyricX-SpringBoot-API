package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Short id;

    @NotBlank
    @Size(max = 30)
    private String language;

    public Language() {

    }

    public Language(@NotBlank @Size(max = 30) String language) {

        this.language = language;
    }

    public Short getId() {

        return id;
    }

    public void setId(Short id) {

        this.id = id;
    }

    public String getLanguage() {

        return language;
    }

    public void setLanguage(String language) {

        this.language = language;
    }

}
