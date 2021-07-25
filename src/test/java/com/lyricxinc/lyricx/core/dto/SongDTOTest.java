package com.lyricxinc.lyricx.core.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class SongDTOTest {
    @Test
    public void givenNullsIgnoredOnClass_whenWritingObjectWithNullField_thenIgnored()
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SongDTO dtoObject = new SongDTO();

        String dtoAsString = mapper.writeValueAsString(dtoObject);

        System.out.println(dtoAsString);
    }
}
