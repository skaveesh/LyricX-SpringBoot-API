package com.lyricxinc.lyricx.core.constant;

public final class Constants {

    public interface FactoryConstants {}

    //todo remove these enums after code analysis
    public enum ContributorFactoryConstants implements FactoryConstants {
        CONTRIBUTOR_TEST_PAYLOAD
    }


    public enum AlbumFactoryConstants implements FactoryConstants {
        UPDATE_ALBUM_PAYLOAD
    }


    public enum RequestAbstractFactoryConstants {
        CONTRIBUTOR_REQUESTS_FACTORY, ALBUM_REQUESTS_FACTORY
    }

    public static final class ErrorMessage{

        private ErrorMessage() {
            // un-initialisable class
        }

        public static final String LYRICX_ERR_00 = "Internal Server Error.";
        public static final String LYRICX_ERR_01 = "Requested API Method Doesn't Exists.";
        public static final String LYRICX_ERR_02 = "Server Database Error Occurred.";
        public static final String LYRICX_ERR_03 = "Error while uploading the image. Image should not exceed the size of 3Mb.";
        public static final String LYRICX_ERR_04 = "Unauthorized user";
        public static final String LYRICX_ERR_05 = "Contributor cannot be found.";
        public static final String LYRICX_ERR_06 = "Something went wrong while creating user account. ";
        public static final String LYRICX_ERR_07 = "Only senior contributors can update verified song.";
        public static final String LYRICX_ERR_08 = "Only senior contributors can update verified artist.";
        public static final String LYRICX_ERR_09 = "Only senior contributors can update verified album.";
        public static final String LYRICX_ERR_10 = "Requested song cannot be found.";
        public static final String LYRICX_ERR_11 = "Requested album cannot be found.";
        public static final String LYRICX_ERR_12 = "Requested artist cannot be found.";
        public static final String LYRICX_ERR_13 = "Error while uploading the file.";
        public static final String LYRICX_ERR_14 = "Requested language cannot be found.";
        public static final String LYRICX_ERR_15 = "Requested resource cannot be found.";
        public static final String LYRICX_ERR_16 = "Provided admin credentials are not valid.";
        public static final String LYRICX_ERR_17 = "Provided contributor credentials are not valid.";
        public static final String LYRICX_ERR_18 = "Provided chanter credentials are not valid.";
        public static final String LYRICX_ERR_19 = "This account hasn't verified yet. Please verify.";
        public static final String LYRICX_ERR_20 = "Required one or more property is empty.";
        public static final String LYRICX_ERR_21 = "Provided E-Mail address is incorrect.";
        public static final String LYRICX_ERR_22 = "Name should not contain any numbers.";
        public static final String LYRICX_ERR_23 = "Entered contact link not valid.";

    }


    public static final class ErrorCode{

        private ErrorCode() {
            // un-initialisable class
        }

        public static final String LYRICX_ERR_00 = "LYRICX_ERR_00";
        public static final String LYRICX_ERR_01 = "LYRICX_ERR_01";
        public static final String LYRICX_ERR_02 = "LYRICX_ERR_02";
        public static final String LYRICX_ERR_03 = "LYRICX_ERR_03";
        public static final String LYRICX_ERR_04 = "LYRICX_ERR_04";
        public static final String LYRICX_ERR_05 = "LYRICX_ERR_05";
        public static final String LYRICX_ERR_06 = "LYRICX_ERR_06";
        public static final String LYRICX_ERR_07 = "LYRICX_ERR_07";
        public static final String LYRICX_ERR_08 = "LYRICX_ERR_08";
        public static final String LYRICX_ERR_09 = "LYRICX_ERR_09";
        public static final String LYRICX_ERR_10 = "LYRICX_ERR_10";
        public static final String LYRICX_ERR_11 = "LYRICX_ERR_11";
        public static final String LYRICX_ERR_12 = "LYRICX_ERR_12";
        public static final String LYRICX_ERR_13 = "LYRICX_ERR_13";
        public static final String LYRICX_ERR_14 = "LYRICX_ERR_14";
        public static final String LYRICX_ERR_15 = "LYRICX_ERR_15";
        public static final String LYRICX_ERR_16 = "LYRICX_ERR_16";
        public static final String LYRICX_ERR_17 = "LYRICX_ERR_17";
        public static final String LYRICX_ERR_18 = "LYRICX_ERR_18";
        public static final String LYRICX_ERR_19 = "LYRICX_ERR_19";
        public static final String LYRICX_ERR_20 = "LYRICX_ERR_20";
        public static final String LYRICX_ERR_21 = "LYRICX_ERR_21";
        public static final String LYRICX_ERR_22 = "LYRICX_ERR_22";
        public static final String LYRICX_ERR_23 = "LYRICX_ERR_23";

    }

    public static final class SuccessMessage{

        private SuccessMessage(){
            // un-initialisable class
        }

        public static final String SUCCESS = "Success";

        public static final String ALBUM_CREATE_SUCCESS = "Album created successfully.";
        public static final String ALBUM_UPDATE_SUCCESS = "Album updated successfully.";
        public static final String ALBUM_ARTWORK_UPDATE_SUCCESS = "Album artwork updated successfully.";
        public static final String ALBUM_ARTWORK_REMOVE_SUCCESS = "Album artwork removed successfully.";

        public static final String ARTIST_CREATE_SUCCESS = "Artist created successfully.";
        public static final String ARTIST_UPDATE_SUCCESS = "Artist updated successfully.";
        public static final String ARTIST_IMAGE_UPDATE_SUCCESS = "Artist image updated successfully.";

        public static final String SONG_CREATE_SUCCESS = "Song added successfully.";
        public static final String SONG_UPDATE_SUCCESS = "Song updated successfully.";
        public static final String SONG_REMOVE_SUCCESS = "Song removed successfully.";
        public static final String SONG_ALBUM_ART_UPDATE_SUCCESS = "Song album art updated successfully.";
        public static final String SONG_ALBUM_ART_REMOVE_SUCCESS = "Song album art removed successfully.";

        public static final String ACCOUNT_CREATE_SUCCESS = "Account created successfully.";

    }

    public static final class SuccessCode{

        private SuccessCode(){
            // un-initialisable class
        }

        public static final String LYRICX_SUC_00 = "LYRICX_SUC_00";

    }

}
