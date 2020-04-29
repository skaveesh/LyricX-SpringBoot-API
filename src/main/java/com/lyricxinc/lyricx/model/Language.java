package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lyricxinc.lyricx.model.validator.group.OnLanguageCreate;
import com.lyricxinc.lyricx.model.validator.group.OnSongCreate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The type Language.
 */
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

    /**
     * Instantiates a new Language.
     */
    public Language() {

    }

    /**
     * Instantiates a new Language.
     *
     * @param languageName the language name
     */
    public Language(@NotBlank @Size(max = 30) String languageName) {

        this.languageName = languageName;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Short getId() {

        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Short id) {

        this.id = id;
    }

    /**
     * Gets language name.
     *
     * @return the language name
     */
    public String getLanguageName() {

        return languageName;
    }

    /**
     * Sets language name.
     *
     * @param languageName the language name
     */
    public void setLanguageName(String languageName) {

        this.languageName = languageName;
    }

    /**
     * Gets language code.
     *
     * @return the language code
     */
    public String getLanguageCode() {

        return languageCode;
    }

    /**
     * Sets language code.
     *
     * @param languageCode should be ISO 639-1:2002 which is a two characters code
     */
    public void setLanguageCode(String languageCode) {

        this.languageCode = languageCode;
    }

}
