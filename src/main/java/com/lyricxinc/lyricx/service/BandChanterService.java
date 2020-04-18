package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.repository.BandChanterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BandChanterService {

    private final BandChanterRepository bandChanterRepository;

    @Autowired
    public BandChanterService(BandChanterRepository bandChanterRepository) {

        this.bandChanterRepository = bandChanterRepository;
    }

    public void addBandChanter(long chanterId, long bandId) {

        bandChanterRepository.addBandAndChanter(chanterId, bandId, false);
    }

    public void removeBandChanter(long chanterId, long bandId) {

        bandChanterRepository.deleteByBand_IdAndChanter_Id(chanterId, bandId);
    }

}
