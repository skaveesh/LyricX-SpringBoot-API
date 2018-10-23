package com.lyricxinc.lyricx.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class VisitorCorrection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id")
    private Song song;

    @NotBlank
    private String submitterName;

    @NotBlank
    private String submitterEmail;

    @NotBlank
    private String correction;

    @CreationTimestamp
    private LocalDateTime addedDate;
}
