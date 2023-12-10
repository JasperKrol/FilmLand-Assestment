package com.filmland.assestment.util;

import java.time.LocalDate;

public class AppDefaultConstants {

    //    Category defaults
    public static final int NL_SERIES_AVAILABLE_CONTENT_DEFAULT_VALUE = 20;
    public static final int NL_MOVIES_AVAILABLE_CONTENT_DEFAULT_VALUE = 10;
    public static final int INTERNATIONAL_MOVIES_AVAILABLE_CONTENT_DEFAULT_VALUE = 20;

    public static final LocalDate LOCAL_DATE_OF_NOW = LocalDate.now();

    public static final String DEFAULT_SUCCES_STATUS = "Login successful";
    public static final String DEFAULT_FAILURE_STATUS = "Login failed";

    // Private constructor to prevent instantiation
    private AppDefaultConstants() {
        throw new IllegalStateException("Utility class");
    }
}
