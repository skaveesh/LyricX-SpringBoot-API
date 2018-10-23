package com.lyricxinc.lyricx.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    private boolean fullControl;

    private boolean suspendedStatus;

    @CreationTimestamp
    private LocalDateTime joinedDate;

    public Admin() {
    }

    public Admin(@NotBlank String name, @NotBlank String email, @NotBlank String password, boolean fullControl, boolean suspendedStatus, LocalDateTime joinedDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.fullControl = fullControl;
        this.suspendedStatus = suspendedStatus;
        this.joinedDate = joinedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFullControl() {
        return fullControl;
    }

    public void setFullControl(boolean fullControl) {
        this.fullControl = fullControl;
    }

    public boolean isSuspendedStatus() {
        return suspendedStatus;
    }

    public void setSuspendedStatus(boolean suspendedStatus) {
        this.suspendedStatus = suspendedStatus;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDateTime joinedDate) {
        this.joinedDate = joinedDate;
    }
}
