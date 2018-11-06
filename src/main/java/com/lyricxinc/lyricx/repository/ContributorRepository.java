package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributorRepository extends JpaRepository<Contributor, Long> {

    /**
     * find a person by email
     * @param email of the contributor
     * @return contributor object corresponds to the email
     */
    Contributor findByEmail(String email);
}
