package com.lyricxinc.lyricx.stomp.controller;

import com.lyricxinc.lyricx.model.socket.inbound.AlbumSuggest;
import com.lyricxinc.lyricx.model.socket.outbound.AlbumSuggestedItem;

/**
 * The interface Album suggest controller.
 */
public interface AlbumSuggestController {

    /**
     * Suggest album album suggested item.
     *
     * @param albumSuggest the album suggest
     * @return the album suggested item
     */
    AlbumSuggestedItem suggestAlbum(AlbumSuggest albumSuggest);
}
