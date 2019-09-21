package com.lyricxinc.lyricx.model;

import com.lyricxinc.lyricx.model.validator.group.OnAlbumCreate;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Contributor {

    @Id
    @NotBlank
    @NotNull(groups = {OnAlbumCreate.class, OnAlbumUpdate.class})
    private String id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String lastName;

    @Size(max = 500)
    private String description;

    private String imgUrl;

    private String contactLink;

    private boolean seniorContributor;

    public Contributor() {

    }

    public Contributor(String id, String firstName, String lastName, String imgUrl, String contactLink) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactLink = contactLink;
        this.imgUrl = imgUrl;
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
