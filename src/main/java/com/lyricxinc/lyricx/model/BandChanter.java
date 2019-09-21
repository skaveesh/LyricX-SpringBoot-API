package com.lyricxinc.lyricx.model;

import javax.persistence.*;

@Entity
public class BandChanter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "bandId", nullable = false)
    private Band band;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "chanterId", nullable = false)
    private Chanter chanter;

    private boolean moderatorStatus;

    public BandChanter() {

    }

    public BandChanter(Band band, Chanter chanter, boolean moderatorStatus) {

        this.band = band;
        this.chanter = chanter;
        this.moderatorStatus = moderatorStatus;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
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
