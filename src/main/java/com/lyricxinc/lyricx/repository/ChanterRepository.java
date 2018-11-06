package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Chanter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChanterRepository extends JpaRepository<Chanter, Long> {

    @Transactional
    void deleteById(long appUserId);
}
