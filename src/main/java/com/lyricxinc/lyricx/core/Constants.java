package com.lyricxinc.lyricx.core;

public final class Constants {

    public interface FactoryConstants {}


    public enum ContributorFactoryConstants implements FactoryConstants {
        CONTRIBUTOR_TEST_PAYLOAD
    }


    public enum AlbumFactoryConstants implements FactoryConstants {
        UPDATE_ALBUM_PAYLOAD
    }


    public enum RequestAbstractFactoryConstants {
        CONTRIBUTOR_REQUESTS_FACTORY, ALBUM_REQUESTS_FACTORY
    }

}
