package com.lyricxinc.lyricx.core.util;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.*;

public class StringValidatorUtil {

    private static void nullCheck(String string) {

        if (string == null)
            throw new ForbiddenException(LYRICX_ERR_20.getErrorMessage(), LYRICX_ERR_20.name());
    }

    public static String validateEmailAddress(String email) {

        nullCheck(email);

        email = email.trim();

        if (!EmailValidator.getInstance().isValid(email))
            throw new ForbiddenException(LYRICX_ERR_21.getErrorMessage(), LYRICX_ERR_21.name());

        return email;
    }

    public static String validateName(String name) {

        nullCheck(name);

        name = name.trim();

        if (name.matches(".*\\d+.*"))
            throw new ForbiddenException(LYRICX_ERR_22.getErrorMessage(), LYRICX_ERR_22.name());

        return name;
    }

    public static String validateContactLink(String url) {

        nullCheck(url);

        url = url.trim();

        if (!UrlValidator.getInstance().isValid(url))
            throw new ForbiddenException(LYRICX_ERR_23.getErrorMessage(), LYRICX_ERR_23.name());

        return url;
    }

}
