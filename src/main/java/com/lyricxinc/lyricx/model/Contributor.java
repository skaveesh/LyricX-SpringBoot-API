package com.lyricxinc.lyricx.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Contributor {

    @Id
    private String id;

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

    public Contributor() {
    }

    public Contributor(@NotBlank String id, @NotBlank @Size(max = 50) String firstName, @NotBlank @Size(max = 50) String lastName, String contactLink) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactLink = contactLink;
        this.seniorContributor = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setDescription(@Size(max = 500) String description) {
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
}
