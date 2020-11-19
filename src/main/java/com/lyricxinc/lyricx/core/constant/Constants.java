package com.lyricxinc.lyricx.core.constant;

/**
 * The type Constants.
 */
public final class Constants {

    public static final class AppConstants{

        private AppConstants() {
            //private constructor to avoid initialization
        }

        public static final int BATCH_SIZE = 5;
    }

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
     * The type Error message and code.
     */
    public enum ErrorMessageAndCode {

        LYRICX_ERR_00( "Internal Server Error."),
        LYRICX_ERR_01("Requested API Method Doesn't Exists."),
        LYRICX_ERR_02("Server Database Error Occurred."),
        LYRICX_ERR_03("Error while uploading the image. Image should not exceed the size of 3Mb."),
        LYRICX_ERR_04("Unauthorized user"),
        LYRICX_ERR_05("Contributor cannot be found."),
        LYRICX_ERR_06("Something went wrong while creating user account. "),
        LYRICX_ERR_07("Only senior contributors can update verified song."),
        LYRICX_ERR_08("Only senior contributors can update verified artist."),
        LYRICX_ERR_09("Only senior contributors can update verified album."),
        LYRICX_ERR_10("Requested song cannot be found."),
        LYRICX_ERR_11("Requested album cannot be found."),
        LYRICX_ERR_12("Requested artist cannot be found."),
        LYRICX_ERR_13("Error while uploading the file."),
        LYRICX_ERR_14("Requested language cannot be found."),
        LYRICX_ERR_15("Requested resource cannot be found."),
        LYRICX_ERR_16("Provided admin credentials are not valid."),
        LYRICX_ERR_17("Provided contributor credentials are not valid."),
        LYRICX_ERR_18("Provided chanter credentials are not valid."),
        LYRICX_ERR_19("This account hasn't verified yet. Please verify."),
        LYRICX_ERR_20("Required one or more property is empty."),
        LYRICX_ERR_21("Provided E-Mail address is incorrect."),
        LYRICX_ERR_22("Name should not contain any numbers."),
        LYRICX_ERR_23("Entered contact link not valid."),
        LYRICX_ERR_24("Could not find image url for the song."),
        LYRICX_ERR_25("Could not find image url for the album."),
        LYRICX_ERR_26("Could not find image url for the artist."),
        LYRICX_ERR_27("Type of suggestion could not be found."),
        LYRICX_ERR_28("No song genres present."),
        LYRICX_ERR_29("Artists not found while updating the song"),
        LYRICX_ERR_30("Genres not found while updating the song"),
        LYRICX_ERR_31("Genres not found while updating the artist");

        private String errorMessage;

        ErrorMessageAndCode(String errorMessage) {

            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {

            return errorMessage;
        }

    }


    /**
     * The type Success message.
     */
    public enum SuccessMessage {

        SUCCESS("Success"),
        ALBUM_CREATE_SUCCESS("Album created successfully."),
        ALBUM_UPDATE_SUCCESS("Album updated successfully."),
        ALBUM_ARTWORK_UPDATE_SUCCESS("Album artwork and details updated successfully."),
        ALBUM_ARTWORK_REMOVE_SUCCESS("Album artwork removed successfully."),
        ARTIST_CREATE_SUCCESS("Artist created successfully."),
        ARTIST_UPDATE_SUCCESS("Artist updated successfully."),
        ARTIST_IMAGE_UPDATE_SUCCESS("Artist image and details updated successfully."),
        SONG_CREATE_SUCCESS("Song added successfully."),
        SONG_UPDATE_SUCCESS("Song updated successfully."),
        SONG_REMOVE_SUCCESS("Song removed successfully."),
        SONG_ALBUM_ART_UPDATE_SUCCESS("Song album art updated successfully."),
        SONG_ALBUM_ART_REMOVE_SUCCESS("Song album art removed successfully."),
        ACCOUNT_CREATE_SUCCESS("Account created successfully.");

        private String successMessage;

        SuccessMessage(String successMessage) {

            this.successMessage = successMessage;
        }

        public String getSuccessMessage() {

            return successMessage;
        }
    }

}
