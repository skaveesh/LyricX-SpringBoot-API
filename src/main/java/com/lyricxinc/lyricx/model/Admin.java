package com.lyricxinc.lyricx.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Admin {

    @Id
    private String id;

    @NotBlank
    private String name;

    private boolean fullControl;

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

}
