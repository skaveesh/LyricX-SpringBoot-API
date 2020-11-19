package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.dto.MetadataResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MetadataService {

    @Value("${com.lyricxinc.lyricx.baseImageBucketUrl}")
    private String baseImageBucketURL;

    public MetadataResponseDTO getAllMetadata() {

        MetadataResponseDTO metadataResponseDTO = new MetadataResponseDTO();
        metadataResponseDTO.setBaseImageBucketURL(baseImageBucketURL);

        return metadataResponseDTO;
    }
}
