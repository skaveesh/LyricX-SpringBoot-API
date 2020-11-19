package com.lyricxinc.lyricx.core.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class SongCreateUpdateRequestDTOTest {
    @Test
    public void givenNullsIgnoredOnClass_whenWritingObjectWithNullField_thenIgnored()
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SongCreateUpdateRequestDTO dtoObject = new SongCreateUpdateRequestDTO();

        String dtoAsString = mapper.writeValueAsString(dtoObject);

        System.out.println(dtoAsString);
    }
}
