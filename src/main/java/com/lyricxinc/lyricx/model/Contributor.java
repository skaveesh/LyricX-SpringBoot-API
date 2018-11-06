package com.lyricxinc.lyricx.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Contributor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(unique = true)
    @Size(min = 5)
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    @Size(min = 3, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String lastName;

    @Size(max = 500)
    private String description;

    @NotBlank
    private String imgUrl;

    private String contactLink;

    private boolean seniorContributor;

    private boolean suspendStatus;

    @CreationTimestamp
    private LocalDateTime joinedDate;

    public Contributor() {
    }

    public Contributor(@NotBlank String email, @NotBlank String password, @NotBlank @Size(max = 50) String firstName, @NotBlank @Size(max = 50) String lastName, @Size(max = 500) String description, @NotBlank String imgUrl, String contactLink, boolean seniorContributor, boolean suspendStatus) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.imgUrl = imgUrl;
        this.contactLink = contactLink;
        this.seniorContributor = seniorContributor;
        this.suspendStatus = suspendStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContactLink() {
        return contactLink;
    }

    public void setContactLink(String contactLink) {
        this.contactLink = contactLink;
    }

    public boolean isSeniorContributor() {
        return seniorContributor;
    }

    public void setSeniorContributor(boolean seniorContributor) {
        this.seniorContributor = seniorContributor;
    }

    public boolean isSuspendStatus() {
        return suspendStatus;
    }

    public void setSuspendStatus(boolean suspendStatus) {
        this.suspendStatus = suspendStatus;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

}
