package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Chanter;
import com.lyricxinc.lyricx.repository.ChanterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChanterService {

    private final ChanterRepository chanterRepository;

    @Autowired
    public ChanterService(ChanterRepository chanterRepository) {
        this.chanterRepository = chanterRepository;
    }

    public void addChanter(String googleIdToken){
        Chanter chanter = new Chanter(googleIdToken, false);
        chanterRepository.save(chanter);
    }

    public void removeAppUser(long chanterId){
        chanterRepository.deleteById(chanterId);
    }
}
