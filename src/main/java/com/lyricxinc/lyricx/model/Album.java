package com.lyricxinc.lyricx.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Year;
import java.util.Set;

@Entity
@Table
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id")
    private Artist artist;

    private Year year;

    @NotBlank
    private String name;

    @NotBlank
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id")
    private Contributor addedBy;

    private boolean approvedStatus;

    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Song> songs;
}
