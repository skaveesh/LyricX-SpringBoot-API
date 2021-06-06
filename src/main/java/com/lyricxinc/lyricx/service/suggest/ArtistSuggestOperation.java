package com.lyricxinc.lyricx.service.suggest;

import com.lyricxinc.lyricx.model.socket.outbound.ArtistSuggestedItem;
import com.lyricxinc.lyricx.model.socket.outbound.SuggestedItem;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class ArtistSuggestOperation implements MediaSuggestOperation {
    //TODO implement Lucene like search engine here

    @Override
    public <T extends SuggestedItem> void createMedia(Set<T> albumSuggestedItemTreeSet) {

        albumSuggestedItemTreeSet.forEach((T x) -> System.out.println("in create " + ((ArtistSuggestedItem) x).getArtistName() + " " + ((ArtistSuggestedItem) x).getSurrogateKey()));
    }

    @Override
    public <T extends SuggestedItem> Set<T> readMedia(String name) {

        TreeSet<ArtistSuggestedItem> x = new TreeSet<>(Comparator.comparing((artist)-> artist.getArtistName() + '$' + artist.getSurrogateKey()));
        x.add(new ArtistSuggestedItem("artist5", "ASam5"));
        x.add(new ArtistSuggestedItem("artist2", "ASam2"));
        x.add(new ArtistSuggestedItem("artist3", "ASamagw"));
        x.add(new ArtistSuggestedItem("artist0", "ASam0"));
        x.add(new ArtistSuggestedItem("artist4", "ASambsv"));

        return (TreeSet<T>) x;
    }

    @Override
    public void updateMedia(String surrogateKey, String newName) {

    }

    @Override
    public void removeMedia(String surrogateKey) {

    }

}
