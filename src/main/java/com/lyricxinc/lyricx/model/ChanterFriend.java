package com.lyricxinc.lyricx.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ChanterFriend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "chanterId", nullable = false)
    private Chanter chanter;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "friendId", nullable = false)
    private Chanter friend;

    @CreationTimestamp
    private LocalDateTime friendSince;

}
