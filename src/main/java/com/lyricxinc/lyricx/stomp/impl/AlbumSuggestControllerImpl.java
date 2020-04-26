package com.lyricxinc.lyricx.stomp.impl;

import com.lyricxinc.lyricx.model.socket.inbound.AlbumSuggest;
import com.lyricxinc.lyricx.model.socket.outbound.AlbumSuggestedItem;
import com.lyricxinc.lyricx.service.AlbumService;
import com.lyricxinc.lyricx.stomp.controller.AlbumSuggestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * The type Album suggest controller.
 */
@Controller
public class AlbumSuggestControllerImpl implements AlbumSuggestController {

    private final AlbumService albumService;

    /**
     * Instantiates a new Album suggest controller.
     *
     * @param albumService the album service
     */
    @Autowired
    public AlbumSuggestControllerImpl(AlbumService albumService) {

        this.albumService = albumService;
    }

    @Override
    @MessageMapping("/suggest/album")
    @SendToUser("/suggested/album")
    public AlbumSuggestedItem suggestAlbum(AlbumSuggest albumSuggest) {

        albumService.suggestAlbums(albumSuggest);

        return null;
    }

}
