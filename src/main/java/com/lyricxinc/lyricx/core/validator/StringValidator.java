package com.lyricxinc.lyricx.core.validator;

import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

public class StringValidator {

    private static void nullCheck(String string) {

        if (string == null)
            throw new ForbiddenCustomException("Required one or more property is empty.");
    }

    public static String validateEmailAddress(String email) {

        nullCheck(email);

        email = email.trim();

        if (!EmailValidator.getInstance().isValid(email))
            throw new ForbiddenCustomException("Provided E-Mail address is wrong.");

        return email;
    }

    public static String validateName(String name) {

        nullCheck(name);

        name = name.trim();

        if (name.matches(".*\\d+.*"))
            throw new ForbiddenCustomException("Name should not contain any numbers.");

        return name;
    }

    public static String validateContactLink(String url) {

        nullCheck(url);

        url = url.trim();

        if (!UrlValidator.getInstance().isValid(url))
            throw new ForbiddenCustomException("Entered contact link not valid.");

        return url;
    }

}
