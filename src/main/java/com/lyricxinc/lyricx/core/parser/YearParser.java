package com.lyricxinc.lyricx.core.parser;

import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;

import java.time.Year;

public class YearParser {

    public static Year parseYear(String year) {
        Year yearParsed;

        try {
            yearParsed = Year.parse(year);
        } catch (Exception e) {
            throw new ForbiddenCustomException("Entered year cannot be parsed.");
        }

        if (yearParsed.compareTo(Year.now()) > 0)
            throw new ForbiddenCustomException("Year cannot be greater than " + Year.now());

        return yearParsed;
    }
}
