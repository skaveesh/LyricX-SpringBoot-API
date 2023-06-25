package com.lyricxinc.lyricx.service.suggest;

import com.lyricxinc.lyricx.model.socket.outbound.SuggestedItem;

import java.util.Set;

/**
 * The interface Media suggest operation.
 */
public interface MediaSuggestOperation {

    /**
     * Create media.
     *
     * @param mediaSuggestedItemTreeSet the media suggested item tree set
     */
    <T extends SuggestedItem> void createMedia(final Set<T> mediaSuggestedItemTreeSet);

    /**
     * Read media set.
     *
     * @param <T>  the type parameter
     * @param name the name
     * @return the set
     */
    <T extends SuggestedItem> Set<T> readMedia(final String name);

    /**
     * Update media.
     *
     * @param surrogateKey the surrogate key
     * @param newName      the new name
     */
    void updateMedia(final String surrogateKey, final String newName);

    /**
     * Remove media.
     *
     * @param surrogateKey the surrogate key
     */
    void removeMedia(final String surrogateKey);
}
