package org.automation.http;

/**
 * Created by shantonu on 6/3/17.
 */
public class User {
private String name,password;

    public User(String name, String password) {
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
