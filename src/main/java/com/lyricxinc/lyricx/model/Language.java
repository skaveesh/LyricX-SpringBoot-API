package com.lyricxinc.lyricx.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @NotBlank
    @Size(max = 30)
    private String language;


}
