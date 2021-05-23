package com.lyricxinc.lyricx.core.util;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.*;

public class StringValidatorUtil {

    private StringValidatorUtil() {
        // private constructor to forbid instantiation
    }

    private static void checkStringNullity(String string) {

        if (string == null)
        {
            throw new ForbiddenException(LYRICX_ERR_20);
        }
    }

    public static boolean isStringNotEmpty(String string) {

        return string != null && !string.isEmpty();
    }

    public static void checkStringEmpty(String string) {

        checkStringNullity(string);

        if(string.isEmpty())
        {
            throw new ForbiddenException(LYRICX_ERR_20);
        }
    }

    public static String validateEmailAddress(String email) {

        checkStringNullity(email);

        email = email.trim();

        if (!EmailValidator.getInstance().isValid(email))
            throw new ForbiddenException(LYRICX_ERR_21);

        return email;
    }

    public static String validateName(String name) {

        checkStringNullity(name);

        name = name.trim();

        if (name.matches(".*\\d+.*"))
            throw new ForbiddenException(LYRICX_ERR_22);

        return name;
    }

    public static String validateContactLink(String url) {

        checkStringNullity(url);

        url = url.trim();

        if (!UrlValidator.getInstance().isValid(url))
            throw new ForbiddenException(LYRICX_ERR_23);

        return url;
    }

}
