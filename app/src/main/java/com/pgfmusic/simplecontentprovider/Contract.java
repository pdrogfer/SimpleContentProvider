package com.pgfmusic.simplecontentprovider;

/**
 * Created by pedrogonzalezferrandez on 27/12/15.
 */
public class Contract {

    public static final String SCHEME = "content://";
    public static final String AUTHORITY = "com.pgfmusic.simplecontentprovider.College";
    public static final String BASE_URI = SCHEME + AUTHORITY;
    public static final String PATH = "/students";
}
