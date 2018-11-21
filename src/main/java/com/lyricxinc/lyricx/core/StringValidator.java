package com.lyricxinc.lyricx.core;

import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

public class StringValidator {

    public static String validateEmailAddress(String email) {
        email = email.trim();

        if(!EmailValidator.getInstance().isValid(email))
            throw new ForbiddenCustomException("Provided E-Mail address is wrong.");

        return email;
    }

    public static String validateName(String name) {
        name = name.trim();

        if(name.matches(".*\\d+.*"))
            throw new ForbiddenCustomException("Name should not contain any numbers.");

        return name;
    }

    public static String validateContactLink(String url) {
        url = url.trim();

        if(!UrlValidator.getInstance().isValid(url))
            throw new ForbiddenCustomException("Entered contact link not valid.");

        return url;
    }
}
