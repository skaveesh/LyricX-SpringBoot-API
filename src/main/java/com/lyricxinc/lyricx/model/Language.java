package com.lyricxinc.lyricx.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @NotBlank
    @Size(max = 30)
    private String language;

    public Language() {
    }

    public Language(@NotBlank @Size(max = 30) String language) {
        this.language = language;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
