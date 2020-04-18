package com.lyricxinc.lyricx.service.firebase;

/**
 * The interface Media suggest operation.
 */
public interface MediaSuggestOperation {

    /**
     * Create media.
     *
     * @param surrogateKey the surrogate key
     * @param name         the name
     */
    void createMedia(String surrogateKey, String name);

    /**
     * Update media.
     *
     * @param surrogateKey the surrogate key
     * @param newName      the new name
     */
    void updateMedia(String surrogateKey, String newName);

    /**
     * Remove media.
     *
     * @param surrogateKey the surrogate key
     */
    void removeMedia(String surrogateKey);
}
