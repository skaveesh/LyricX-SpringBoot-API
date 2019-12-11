package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Admin {

    @Id
    @JsonIgnore
    private String id;

    @NotBlank
    private String name;

    private boolean fullControl;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime addedDate;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastModifiedDate;

    public Admin() {

    }

    public Admin(@NotBlank String id, @NotBlank String name, boolean fullControl) {

        this.id = id;
        this.name = name;
        this.fullControl = fullControl;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public boolean isFullControl() {

        return fullControl;
    }

    public void setFullControl(boolean fullControl) {

        this.fullControl = fullControl;
    }

    public LocalDateTime getAddedDate() {

        return addedDate;
    }

    public LocalDateTime getLastModifiedDate() {

        return lastModifiedDate;
    }

}
