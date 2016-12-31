package org.automation.utils.models;

/**
 * Created by shantonu on 7/10/16.
 * this is email body
 */
public class Email {
    private String title;
    private String header;
    private String body;
    private String signeture;
    private String[] attachmentPaths;

    public Email(String title, String signeture, String body, String header, String[] attachmentPaths) {
        this.title = title;
        this.signeture = signeture;
        this.body = body;
        this.header = header;
        this.attachmentPaths = attachmentPaths;
    }

    public Email(String body) {
        this.body = body;
    }

    public Email(String title, String header, String body, String signeture) {
        this.title = title;
        this.header = header;
        this.body = body;
        this.signeture = signeture;
    }
    public Email(){}
}
