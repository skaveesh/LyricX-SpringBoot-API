package com.lyricxinc.lyricx.service.suggest;

import com.lyricxinc.lyricx.model.socket.outbound.AlbumSuggestedItem;
import com.lyricxinc.lyricx.model.socket.outbound.SuggestedItem;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * The type Album suggest operation.
 */
public class AlbumSuggestOperation implements MediaSuggestOperation {

    //TODO implement Lucene like search engine here

    @Override
    public <T extends SuggestedItem> void createMedia(Set<T> albumSuggestedItemTreeSet) {
        albumSuggestedItemTreeSet.forEach((T x)-> System.out.println("in create " + ((AlbumSuggestedItem)x).getAlbumName() + " " + ((AlbumSuggestedItem)x).getSurrogateKey()));
    }

    @Override
    public <T extends SuggestedItem> Set<T> readMedia(String name) {

        TreeSet<AlbumSuggestedItem> x = new TreeSet<>(Comparator.comparing((album)-> album.getAlbumName() + '$' + album.getSurrogateKey()));
        x.add(new AlbumSuggestedItem("a5", "sam5"));
        x.add(new AlbumSuggestedItem("a2", "sam2"));
        x.add(new AlbumSuggestedItem("a3", "samagw"));
        x.add(new AlbumSuggestedItem("a0", "sam0"));
        x.add(new AlbumSuggestedItem("a4", "sambsv"));

       return (TreeSet<T>) x;
    }

    @Override
    public void updateMedia(String surrogateKey, String newName) {

    }

    @Override
    public void removeMedia(String surrogateKey) {

    }

}
