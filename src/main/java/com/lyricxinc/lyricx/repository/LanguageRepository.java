package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Language repository.
 */
@Repository
public interface LanguageRepository extends JpaRepository<Language, Short> {

    /**
     * Find by language name optional.
     *
     * @param languageName the language name
     * @return the optional
     */
    Optional<Language> findByLanguageName(String languageName);

    /**
     * Find by language code optional.
     *
     * @param languageCode the language code
     * @return the optional
     */
    Optional<Language> findByLanguageCode(String languageCode);

}
