package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ChanterFriend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "chanterId", nullable = false)
    @JsonBackReference(value = "chantersReferenceChanter")
    private Chanter chanter;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "friendId", nullable = false)
    @JsonBackReference(value = "friendsReferenceFriend")
    private Chanter friend;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime friendSince;

    public ChanterFriend() {

    }

    public ChanterFriend(Chanter chanter, Chanter friend) {

        this.chanter = chanter;
        this.friend = friend;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

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
