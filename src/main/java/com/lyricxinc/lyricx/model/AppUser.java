package com.lyricxinc.lyricx.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(unique = true, updatable = false)
    private String googleIdToken;

    private boolean disabledStatus;

    @CreationTimestamp
    private LocalDateTime joinedDate;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserInGroup> usersInGroup;

}
