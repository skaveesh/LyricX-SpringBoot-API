package com.lyricxinc.lyricx.core.util;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorCode;
import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessage;

public class StringValidatorUtil {

    private static void nullCheck(String string) {

        if (string == null)
            throw new ForbiddenException(ErrorMessage.LYRICX_ERR_20, ErrorCode.LYRICX_ERR_20);
    }

    public static String validateEmailAddress(String email) {

        nullCheck(email);

        email = email.trim();

        if (!EmailValidator.getInstance().isValid(email))
            throw new ForbiddenException(ErrorMessage.LYRICX_ERR_21, ErrorCode.LYRICX_ERR_21);

        return email;
    }

    public static String validateName(String name) {

        nullCheck(name);

        name = name.trim();

        if (name.matches(".*\\d+.*"))
            throw new ForbiddenException(ErrorMessage.LYRICX_ERR_22, ErrorCode.LYRICX_ERR_22);

        return name;
    }

    public static String validateContactLink(String url) {

        nullCheck(url);

        url = url.trim();

        if (!UrlValidator.getInstance().isValid(url))
            throw new ForbiddenException(ErrorMessage.LYRICX_ERR_23, ErrorCode.LYRICX_ERR_23);

        return url;
    }

}
