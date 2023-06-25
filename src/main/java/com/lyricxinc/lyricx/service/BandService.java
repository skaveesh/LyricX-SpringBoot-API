package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Band;
import com.lyricxinc.lyricx.model.BandChanter;
import com.lyricxinc.lyricx.repository.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class BandService {

    private final BandRepository bandRepository;

    @Autowired
    public BandService(BandRepository bandRepository) {

        this.bandRepository = bandRepository;
    }

    public Set<BandChanter> getBandChanters(long bandId) {

        Band band = bandRepository.findById(bandId).orElse(null);

        if (band != null)
            return band.getBandChanters();
        else
            return null;
    }

    public void addBand(String bandName) {

        Band band = new Band(bandName);
        bandRepository.save(band);
    }

    public void removeBand(long bandId) {

        bandRepository.deleteById(bandId);
    }

}
