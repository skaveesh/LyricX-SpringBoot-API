package com.lyricxinc.lyricx.core;

public class NameRefine {
    public static String replaceWithDash(String name){
        return name.trim().replaceAll("[\\W_]", "-").toLowerCase().replaceAll("^-+", "").replaceAll("-+$", "").replaceAll("-+", "-");
    }
}
