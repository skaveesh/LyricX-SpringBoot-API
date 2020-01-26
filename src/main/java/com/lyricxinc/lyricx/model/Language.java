package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lyricxinc.lyricx.model.validator.group.OnLanguageCreate;
import com.lyricxinc.lyricx.model.validator.group.OnSongCreate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Short id;

    @Size(max = 30)
    @NotBlank(groups = {OnLanguageCreate.class})
    private String languageName;

    @Column(unique = true, nullable = false)
    @NotBlank(groups = {OnSongCreate.class, OnLanguageCreate.class})
    @Size(max = 2, min = 2)
    private String languageCode;

    public Language() {

    }

    public Language(@NotBlank @Size(max = 30) String languageName) {

        this.languageName = languageName;
    }

    public Short getId() {

        return id;
    }

    public void setId(Short id) {

        this.id = id;
    }

    public String getLanguageName() {

        return languageName;
    }

    public void setLanguageName(String languageName) {

        this.languageName = languageName;
    }

    public String getLanguageCode() {

        return languageCode;
    }

    public void setLanguageCode(String languageCode) {

        this.languageCode = languageCode;
    }

}
