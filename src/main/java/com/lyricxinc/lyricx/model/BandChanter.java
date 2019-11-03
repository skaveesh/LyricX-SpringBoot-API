package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BandChanter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @CreationTimestamp
    private LocalDateTime addedDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "bandId", nullable = false)
    @JsonBackReference(value = "bandChantersReferenceBand")
    private Band band;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "chanterId", nullable = false)
    @JsonBackReference(value = "bandChantersReferenceChanter")
    private Chanter chanter;

    private boolean moderatorStatus;

    public BandChanter() {

    }

    public BandChanter(Band band, Chanter chanter, boolean moderatorStatus) {

        this.band = band;
        this.chanter = chanter;
        this.moderatorStatus = moderatorStatus;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public LocalDateTime getAddedDate() {

        return addedDate;
    }

    public Band getBand() {

        return band;
    }

    public void setBand(Band band) {

        this.band = band;
    }

    public Chanter getChanter() {

        return chanter;
    }

    public void setChanter(Chanter chanter) {

        this.chanter = chanter;
    }

    public boolean isModeratorStatus() {

        return moderatorStatus;
    }

    public void setModeratorStatus(boolean moderatorStatus) {

        this.moderatorStatus = moderatorStatus;
    }

}
