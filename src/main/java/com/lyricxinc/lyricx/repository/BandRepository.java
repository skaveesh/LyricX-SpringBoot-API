package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface BandRepository extends JpaRepository<Band, Long> {

    @Transactional
    void deleteById(long bandId);

}
