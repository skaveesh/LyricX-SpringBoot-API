package com.lyricxinc.lyricx.stomp.impl;

import com.lyricxinc.lyricx.model.socket.inbound.ArtistSuggest;
import com.lyricxinc.lyricx.model.socket.outbound.ArtistSuggestedItem;
import com.lyricxinc.lyricx.service.ArtistService;
import com.lyricxinc.lyricx.stomp.controller.ArtistSuggestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.SortedSet;

/**
 * The type Artist suggest controller.
 */
@Controller
public class ArtistSuggestControllerImpl implements ArtistSuggestController {

    private final ArtistService artistService;

    /**
     * Instantiates a new Artist suggest controller.
     *
     * @param artistService the artist service
     */
    @Autowired
    public ArtistSuggestControllerImpl(ArtistService artistService) {

        this.artistService = artistService;
    }

    @Override
    @MessageMapping("/suggest/artist")
    @SendToUser("/suggested/artist")
    public SortedSet<ArtistSuggestedItem> suggestArtist(ArtistSuggest artistSuggest) {

        return artistService.suggestArtists(artistSuggest);
    }

}
