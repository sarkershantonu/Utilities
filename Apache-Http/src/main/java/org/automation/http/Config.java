package org.automation.http;

import javafx.beans.property.ReadOnlyProperty;

/**
 * Created by shantonu on 10/20/16.
 */
public class Config {
    public static final String BASE_URL ="" ;
    public static final String RCAS_URL ="" ;
    public static final String ENV = "";

    public static final String PCS_WS_URL = "";

    public class FIRST_USER {
        private String name;
        private String password;

        public static String getName() {
            return name;
        }

        public static String getPassword() {
            return password;
        }
    }
}
