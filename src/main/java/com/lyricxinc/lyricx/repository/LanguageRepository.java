package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Short> {

    Language findByLanguage(String name);
}
