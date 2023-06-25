package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * The type Contributor.
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Contributor {

    //todo    @NotBlank(groups = {OnAlbumCreate.class, OnAlbumUpdate.class})
    @Id
    @NotBlank
    private String id;

    @Size(min = 3, max = 50)
    private String firstName;

    @Size(min = 3, max = 50)
    private String lastName;

    @Size(max = 500)
    private String description;

    private String imgUrl;

    private String contactLink;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean seniorContributor;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime addedDate;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastModifiedDate;

    /**
     * Instantiates a new Contributor.
     */
    public Contributor() {

    }

    /**
     * Instantiates a new Contributor.
     *
     * @param id          the id
     * @param firstName   the first name
     * @param lastName    the last name
     * @param imgUrl      the img url
     * @param contactLink the contact link
     */
    public Contributor(String id, String firstName, String lastName, String imgUrl, String contactLink) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactLink = contactLink;
        this.imgUrl = imgUrl;
        this.seniorContributor = false;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {

        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {

        this.id = id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {

        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {

        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {

        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(@Size(max = 500) String description) {

        this.description = description;
    }

    /**
     * Gets img url.
     *
     * @return the img url
     */
    public String getImgUrl() {

        return imgUrl;
    }

    /**
     * Sets img url.
     *
     * @param imgUrl the img url
     */
    public void setImgUrl(String imgUrl) {

        this.imgUrl = imgUrl;
    }

    /**
     * Gets contact link.
     *
     * @return the contact link
     */
    public String getContactLink() {

        return contactLink;
    }

    /**
     * Sets contact link.
     *
     * @param contactLink the contact link
     */
    public void setContactLink(String contactLink) {

        this.contactLink = contactLink;
    }

    /**
     * Is senior contributor Boolean.
     *
     * @return the Boolean
     */
    public Boolean isSeniorContributor() {

        return seniorContributor;
    }

    /**
     * Sets senior contributor.
     *
     * @param seniorContributor the senior contributor
     */
    public void setSeniorContributor(Boolean seniorContributor) {

        this.seniorContributor = seniorContributor;
    }

    /**
     * Gets added date.
     *
     * @return the added date
     */
    public LocalDateTime getAddedDate() {

        return addedDate;
    }

    /**
     * Gets last modified date.
     *
     * @return the last modified date
     */
    public LocalDateTime getLastModifiedDate() {

        return lastModifiedDate;
    }

}
