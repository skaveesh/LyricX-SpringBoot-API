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

    public ChanterFriend() {

    }

    public ChanterFriend(Chanter chanter, Chanter friend) {

        this.chanter = chanter;
        this.friend = friend;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public Chanter getChanter() {

        return chanter;
    }

    public void setChanter(Chanter chanter) {

        this.chanter = chanter;
    }

    public Chanter getFriend() {

        return friend;
    }

    public void setFriend(Chanter friend) {

        this.friend = friend;
    }

    public LocalDateTime getFriendSince() {

        return friendSince;
    }

}
