package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Chanter;
import com.lyricxinc.lyricx.repository.ChanterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChanterService {

    private final ChanterRepository chanterRepository;

    @Autowired
    public ChanterService(ChanterRepository chanterRepository) {

        this.chanterRepository = chanterRepository;
    }

    public Chanter getChanterById(String id) {

        return chanterRepository.findById(id).orElse(null);
    }

    public void addChanter(String id) {

        Chanter chanter = new Chanter(id);
        chanterRepository.save(chanter);
    }

    public void removeAppUser(String chanterId) {

        chanterRepository.deleteById(chanterId);
    }

}
