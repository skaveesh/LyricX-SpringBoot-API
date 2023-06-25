package com.lyricxinc.lyricx.stomp.controller;

import com.lyricxinc.lyricx.model.socket.inbound.AlbumSuggest;
import com.lyricxinc.lyricx.model.socket.outbound.AlbumSuggestedItem;

import java.util.SortedSet;

/**
 * The interface Album suggest controller.
 */
public interface AlbumSuggestController {

    /**
     * Suggest album sorted set.
     *
     * @param albumSuggest the album suggest
     * @return the sorted set
     */
    SortedSet<AlbumSuggestedItem> suggestAlbum(AlbumSuggest albumSuggest);
}
