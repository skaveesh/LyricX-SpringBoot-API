package com.lyricxinc.lyricx.stomp.controller;

import com.lyricxinc.lyricx.model.socket.inbound.ArtistSuggest;
import com.lyricxinc.lyricx.model.socket.outbound.ArtistSuggestedItem;

import java.util.SortedSet;

/**
 * The interface Artist suggest controller.
 */
public interface ArtistSuggestController {

    /**
     * Suggest artist sorted set.
     *
     * @param artistSuggest the artist suggest
     * @return the sorted set
     */
    SortedSet<ArtistSuggestedItem> suggestArtist(ArtistSuggest artistSuggest);
}
