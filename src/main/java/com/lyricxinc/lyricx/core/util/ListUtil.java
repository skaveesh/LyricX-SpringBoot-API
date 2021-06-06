package com.lyricxinc.lyricx.core.util;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class ListUtil {

    private ListUtil() {

        super();
    }

    public static <T> Stream<T> fromNullableSet(Set<T> set) {

        return Optional.ofNullable(set).map(Set::stream).orElseGet(Stream::empty);
    }

    public static <T> boolean hasItems(Set<T> set) {

        return Optional.ofNullable(set).orElse(Collections.emptySet()).size() > 0;
    }

}
