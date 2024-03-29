package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributorRepository extends JpaRepository<Contributor, String> {

}
