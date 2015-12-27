package com.pgfmusic.simplecontentprovider;

/**
 * Created by pedrogonzalezferrandez on 27/12/15.
 */
public class Contract {

    public static final String TAG = "app";

    public static final String DATABASE_NAME = "College";
    static final String STUDENTS_TABLE_NAME = "students";

    public static final String SCHEME = "content://";
    public static final String AUTHORITY = "com.pgfmusic.simplecontentprovider";
    public static final String BASE_URI = SCHEME + AUTHORITY;
    public static final String PATH = "/students";
}
