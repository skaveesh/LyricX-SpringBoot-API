package com.lyricxinc.lyricx.model;

import javax.persistence.*;

@Entity
public class GroupUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id")
    private Group group;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id")
    private AppUser appUser;

    private boolean moderatorStatus;

    public GroupUser() {
    }

    public GroupUser(Group group, AppUser appUser, boolean moderatorStatus) {
        this.group = group;
        this.appUser = appUser;
        this.moderatorStatus = moderatorStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public boolean isModeratorStatus() {
        return moderatorStatus;
    }

    public void setModeratorStatus(boolean moderatorStatus) {
        this.moderatorStatus = moderatorStatus;
    }
}
