package com.lyricxinc.lyricx.core.constant;

/**
 * The type Constants.
 */
public final class Constants {

    /**
     * The interface Factory constants.
     */
    public interface FactoryConstants {}


    /**
     * The enum Contributor factory constants.
     */
    //todo remove these enums after code analysis
    public enum ContributorFactoryConstants implements FactoryConstants {
        /**
         * Contributor test payload contributor factory constants.
         */
        CONTRIBUTOR_TEST_PAYLOAD
    }


    /**
     * The enum Album factory constants.
     */
    public enum AlbumFactoryConstants implements FactoryConstants {
        /**
         * Update album payload album factory constants.
         */
        UPDATE_ALBUM_PAYLOAD
    }


    /**
     * The enum Request abstract factory constants.
     */
    public enum RequestAbstractFactoryConstants {
        /**
         * Contributor requests factory request abstract factory constants.
         */
        CONTRIBUTOR_REQUESTS_FACTORY,
        /**
         * Album requests factory request abstract factory constants.
         */
        ALBUM_REQUESTS_FACTORY
    }


    /**
     * The type Error message.
     */
    public static final class ErrorMessage {

        private ErrorMessage() {
            // un-initialisable class
        }

        /**
         * The constant LYRICX_ERR_00.
         */
        public static final String LYRICX_ERR_00 = "Internal Server Error.";
        /**
         * The constant LYRICX_ERR_01.
         */
        public static final String LYRICX_ERR_01 = "Requested API Method Doesn't Exists.";
        /**
         * The constant LYRICX_ERR_02.
         */
        public static final String LYRICX_ERR_02 = "Server Database Error Occurred.";
        /**
         * The constant LYRICX_ERR_03.
         */
        public static final String LYRICX_ERR_03 = "Error while uploading the image. Image should not exceed the size of 3Mb.";
        /**
         * The constant LYRICX_ERR_04.
         */
        public static final String LYRICX_ERR_04 = "Unauthorized user";
        /**
         * The constant LYRICX_ERR_05.
         */
        public static final String LYRICX_ERR_05 = "Contributor cannot be found.";
        /**
         * The constant LYRICX_ERR_06.
         */
        public static final String LYRICX_ERR_06 = "Something went wrong while creating user account. ";
        /**
         * The constant LYRICX_ERR_07.
         */
        public static final String LYRICX_ERR_07 = "Only senior contributors can update verified song.";
        /**
         * The constant LYRICX_ERR_08.
         */
        public static final String LYRICX_ERR_08 = "Only senior contributors can update verified artist.";
        /**
         * The constant LYRICX_ERR_09.
         */
        public static final String LYRICX_ERR_09 = "Only senior contributors can update verified album.";
        /**
         * The constant LYRICX_ERR_10.
         */
        public static final String LYRICX_ERR_10 = "Requested song cannot be found.";
        /**
         * The constant LYRICX_ERR_11.
         */
        public static final String LYRICX_ERR_11 = "Requested album cannot be found.";
        /**
         * The constant LYRICX_ERR_12.
         */
        public static final String LYRICX_ERR_12 = "Requested artist cannot be found.";
        /**
         * The constant LYRICX_ERR_13.
         */
        public static final String LYRICX_ERR_13 = "Error while uploading the file.";
        /**
         * The constant LYRICX_ERR_14.
         */
        public static final String LYRICX_ERR_14 = "Requested language cannot be found.";
        /**
         * The constant LYRICX_ERR_15.
         */
        public static final String LYRICX_ERR_15 = "Requested resource cannot be found.";
        /**
         * The constant LYRICX_ERR_16.
         */
        public static final String LYRICX_ERR_16 = "Provided admin credentials are not valid.";
        /**
         * The constant LYRICX_ERR_17.
         */
        public static final String LYRICX_ERR_17 = "Provided contributor credentials are not valid.";
        /**
         * The constant LYRICX_ERR_18.
         */
        public static final String LYRICX_ERR_18 = "Provided chanter credentials are not valid.";
        /**
         * The constant LYRICX_ERR_19.
         */
        public static final String LYRICX_ERR_19 = "This account hasn't verified yet. Please verify.";
        /**
         * The constant LYRICX_ERR_20.
         */
        public static final String LYRICX_ERR_20 = "Required one or more property is empty.";
        /**
         * The constant LYRICX_ERR_21.
         */
        public static final String LYRICX_ERR_21 = "Provided E-Mail address is incorrect.";
        /**
         * The constant LYRICX_ERR_22.
         */
        public static final String LYRICX_ERR_22 = "Name should not contain any numbers.";
        /**
         * The constant LYRICX_ERR_23.
         */
        public static final String LYRICX_ERR_23 = "Entered contact link not valid.";
        /**
         * The constant LYRICX_ERR_24.
         */
        public static final String LYRICX_ERR_24 = "Could not find image url for the song.";
        /**
         * The constant LYRICX_ERR_25.
         */
        public static final String LYRICX_ERR_25 = "Could not find image url for the album.";
        /**
         * The constant LYRICX_ERR_26.
         */
        public static final String LYRICX_ERR_26 = "Could not find image url for the artist.";

    }


    /**
     * The type Error code.
     */
    public static final class ErrorCode {

        private ErrorCode() {
            // un-initialisable class
        }

        /**
         * The constant LYRICX_ERR_00.
         */
        public static final String LYRICX_ERR_00 = "LYRICX_ERR_00";
        /**
         * The constant LYRICX_ERR_01.
         */
        public static final String LYRICX_ERR_01 = "LYRICX_ERR_01";
        /**
         * The constant LYRICX_ERR_02.
         */
        public static final String LYRICX_ERR_02 = "LYRICX_ERR_02";
        /**
         * The constant LYRICX_ERR_03.
         */
        public static final String LYRICX_ERR_03 = "LYRICX_ERR_03";
        /**
         * The constant LYRICX_ERR_04.
         */
        public static final String LYRICX_ERR_04 = "LYRICX_ERR_04";
        /**
         * The constant LYRICX_ERR_05.
         */
        public static final String LYRICX_ERR_05 = "LYRICX_ERR_05";
        /**
         * The constant LYRICX_ERR_06.
         */
        public static final String LYRICX_ERR_06 = "LYRICX_ERR_06";
        /**
         * The constant LYRICX_ERR_07.
         */
        public static final String LYRICX_ERR_07 = "LYRICX_ERR_07";
        /**
         * The constant LYRICX_ERR_08.
         */
        public static final String LYRICX_ERR_08 = "LYRICX_ERR_08";
        /**
         * The constant LYRICX_ERR_09.
         */
        public static final String LYRICX_ERR_09 = "LYRICX_ERR_09";
        /**
         * The constant LYRICX_ERR_10.
         */
        public static final String LYRICX_ERR_10 = "LYRICX_ERR_10";
        /**
         * The constant LYRICX_ERR_11.
         */
        public static final String LYRICX_ERR_11 = "LYRICX_ERR_11";
        /**
         * The constant LYRICX_ERR_12.
         */
        public static final String LYRICX_ERR_12 = "LYRICX_ERR_12";
        /**
         * The constant LYRICX_ERR_13.
         */
        public static final String LYRICX_ERR_13 = "LYRICX_ERR_13";
        /**
         * The constant LYRICX_ERR_14.
         */
        public static final String LYRICX_ERR_14 = "LYRICX_ERR_14";
        /**
         * The constant LYRICX_ERR_15.
         */
        public static final String LYRICX_ERR_15 = "LYRICX_ERR_15";
        /**
         * The constant LYRICX_ERR_16.
         */
        public static final String LYRICX_ERR_16 = "LYRICX_ERR_16";
        /**
         * The constant LYRICX_ERR_17.
         */
        public static final String LYRICX_ERR_17 = "LYRICX_ERR_17";
        /**
         * The constant LYRICX_ERR_18.
         */
        public static final String LYRICX_ERR_18 = "LYRICX_ERR_18";
        /**
         * The constant LYRICX_ERR_19.
         */
        public static final String LYRICX_ERR_19 = "LYRICX_ERR_19";
        /**
         * The constant LYRICX_ERR_20.
         */
        public static final String LYRICX_ERR_20 = "LYRICX_ERR_20";
        /**
         * The constant LYRICX_ERR_21.
         */
        public static final String LYRICX_ERR_21 = "LYRICX_ERR_21";
        /**
         * The constant LYRICX_ERR_22.
         */
        public static final String LYRICX_ERR_22 = "LYRICX_ERR_22";
        /**
         * The constant LYRICX_ERR_23.
         */
        public static final String LYRICX_ERR_23 = "LYRICX_ERR_23";
        /**
         * The constant LYRICX_ERR_24.
         */
        public static final String LYRICX_ERR_24 = "LYRICX_ERR_24";
        /**
         * The constant LYRICX_ERR_25.
         */
        public static final String LYRICX_ERR_25 = "LYRICX_ERR_25";
        /**
         * The constant LYRICX_ERR_26.
         */
        public static final String LYRICX_ERR_26 = "LYRICX_ERR_26";

    }


    /**
     * The type Success message.
     */
    public static final class SuccessMessage {

        private SuccessMessage() {
            // un-initialisable class
        }

        /**
         * The constant SUCCESS.
         */
        public static final String SUCCESS = "Success";

        /**
         * The constant ALBUM_CREATE_SUCCESS.
         */
        public static final String ALBUM_CREATE_SUCCESS = "Album created successfully.";
        /**
         * The constant ALBUM_UPDATE_SUCCESS.
         */
        public static final String ALBUM_UPDATE_SUCCESS = "Album updated successfully.";
        /**
         * The constant ALBUM_ARTWORK_UPDATE_SUCCESS.
         */
        public static final String ALBUM_ARTWORK_UPDATE_SUCCESS = "Album artwork updated successfully.";
        /**
         * The constant ALBUM_ARTWORK_REMOVE_SUCCESS.
         */
        public static final String ALBUM_ARTWORK_REMOVE_SUCCESS = "Album artwork removed successfully.";

        /**
         * The constant ARTIST_CREATE_SUCCESS.
         */
        public static final String ARTIST_CREATE_SUCCESS = "Artist created successfully.";
        /**
         * The constant ARTIST_UPDATE_SUCCESS.
         */
        public static final String ARTIST_UPDATE_SUCCESS = "Artist updated successfully.";
        /**
         * The constant ARTIST_IMAGE_UPDATE_SUCCESS.
         */
        public static final String ARTIST_IMAGE_UPDATE_SUCCESS = "Artist image updated successfully.";

        /**
         * The constant SONG_CREATE_SUCCESS.
         */
        public static final String SONG_CREATE_SUCCESS = "Song added successfully.";
        /**
         * The constant SONG_UPDATE_SUCCESS.
         */
        public static final String SONG_UPDATE_SUCCESS = "Song updated successfully.";
        /**
         * The constant SONG_REMOVE_SUCCESS.
         */
        public static final String SONG_REMOVE_SUCCESS = "Song removed successfully.";
        /**
         * The constant SONG_ALBUM_ART_UPDATE_SUCCESS.
         */
        public static final String SONG_ALBUM_ART_UPDATE_SUCCESS = "Song album art updated successfully.";
        /**
         * The constant SONG_ALBUM_ART_REMOVE_SUCCESS.
         */
        public static final String SONG_ALBUM_ART_REMOVE_SUCCESS = "Song album art removed successfully.";

        /**
         * The constant ACCOUNT_CREATE_SUCCESS.
         */
        public static final String ACCOUNT_CREATE_SUCCESS = "Account created successfully.";

    }


    /**
     * The type Success code.
     */
    public static final class SuccessCode {

        private SuccessCode() {
            // un-initialisable class
        }

        /**
         * The constant LYRICX_SUC_00.
         */
        public static final String LYRICX_SUC_00 = "LYRICX_SUC_00";

    }

}
