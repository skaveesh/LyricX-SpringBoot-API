package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album getAlbum(long id){
        return this.albumRepository.findById(id).orElse(null);
    }

    public void addAlbum(Album album){
        album.setAlbumUrl(UUID.randomUUID().toString().replace("-", ""));
        this.albumRepository.save(album);
    }

    public void updateAlbum(Album album){
        this.albumRepository.save(album);
    }

    public void removeAlbum(long id){
        this.albumRepository.deleteById(id);
    }
}
