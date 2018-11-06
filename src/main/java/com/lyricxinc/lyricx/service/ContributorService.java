package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.repository.ContributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContributorService {

    private final ContributorRepository contributorRepository;

    @Autowired
    public ContributorService(ContributorRepository contributorRepository) {
        this.contributorRepository = contributorRepository;
    }

    public Contributor getContributorByEmail(String email){
        return contributorRepository.findByEmail(email);
    }

    public void addContributor(Contributor contributor){
        contributorRepository.save(contributor);
    }

    public void updateContributor(Contributor contributor){
        contributorRepository.save(contributor);
    }

    public void removeContributerByEmail(String email){
        contributorRepository.delete(this.getContributorByEmail(email));
    }
}
