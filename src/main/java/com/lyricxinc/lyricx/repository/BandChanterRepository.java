package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.BandChanter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BandChanterRepository extends JpaRepository<BandChanter, Long> {

    @Modifying
    @Query(value = "insert into band_chanter (chanter_id, band_id, moderator_status) values(:chanter_id, :band_id, :moderator_status)", nativeQuery = true)
    @Transactional
    int addBandAndChanter(@Param("chanter_id") Long chanter_id, @Param("band_id") Long band_id, @Param("moderator_status") Boolean moderator_status);

    @Transactional
    int deleteByBand_IdAndChanter_Id(long bandId, long chanterId);

    BandChanter findByBand_IdAndChanter_Id(long bandId, long chanterId);

}
