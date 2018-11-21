package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Chanter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChanterRepository extends JpaRepository<Chanter, String> {

}
