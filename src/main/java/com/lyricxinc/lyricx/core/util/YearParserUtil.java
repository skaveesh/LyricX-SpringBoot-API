package com.lyricxinc.lyricx.core.util;

import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;

import java.time.Year;

public class YearParserUtil {

    public static Year parseYear(String year, boolean throwExceptionIfYearIsGreaterThanNow) {

        Year yearParsed;

        try
        {
            yearParsed = Year.parse(year);
        } catch (Exception e)
        {
            throw new ForbiddenCustomException("Entered year cannot be parsed.");
        }

        if (throwExceptionIfYearIsGreaterThanNow && yearParsed.compareTo(Year.now()) > 0)
            throw new ForbiddenCustomException("Year cannot be greater than " + Year.now());

        return yearParsed;
    }

}
