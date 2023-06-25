package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.dto.MetadataDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MetadataService {

    @Value("${com.lyricxinc.lyricx.baseImageBucketUrl}")
    private String baseImageBucketURL;

    public MetadataDTO getAllMetadata() {

        MetadataDTO metadataDTO = new MetadataDTO();
        metadataDTO.setBaseImageBucketURL(baseImageBucketURL);

        return metadataDTO;
    }
}
