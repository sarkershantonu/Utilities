package org.automation.http;

/**
 * Created by shantonu on 6/3/17.
 */
public enum User {
    FIRST_USER("",""), SECOND_USER("","");//put your user names

    private String name, password;

    User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
