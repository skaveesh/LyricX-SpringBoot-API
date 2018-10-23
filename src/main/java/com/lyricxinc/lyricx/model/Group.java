package com.lyricxinc.lyricx.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String groupName;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GroupUser> groupUsers;

    public Group() {
    }

    public Group(@NotBlank String groupName, LocalDateTime createdDate) {
        this.groupName = groupName;
        this.createdDate = createdDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Set<GroupUser> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(Set<GroupUser> groupUsers) {
        this.groupUsers = groupUsers;
    }
}
