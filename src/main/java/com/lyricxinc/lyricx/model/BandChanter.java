package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BandChanter {

    @Id
    @SequenceGenerator(name = "band_chanter_id_seq", sequenceName = "band_chanter_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "band_chanter_id_seq")
    @JsonIgnore
    private Long id;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime addedDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "bandId", nullable = false)
    @JsonBackReference(value = "bandChantersReferenceBand")
    private Band band;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "chanterId", nullable = false)
    @JsonBackReference(value = "bandChantersReferenceChanter")
    private Chanter chanter;

    private Boolean moderatorStatus;

    public BandChanter() {

    }

    public BandChanter(Band band, Chanter chanter, Boolean moderatorStatus) {

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

    public Boolean isModeratorStatus() {

        return moderatorStatus;
    }

    public void setModeratorStatus(Boolean moderatorStatus) {

        this.moderatorStatus = moderatorStatus;
    }

}
