package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Short> {

    Optional<Language> findByLanguageName(String languageName);

    Optional<Language> findByLanguageCode(String languageCode);

}
