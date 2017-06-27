package org.automation.email;


import org.automation.models.Email;

/**
 * Created by shantonu on 7/10/16.
 * email sending using java
 * todo
 */
public class EmailUtil {

    private String toEmail = null;
    private String fromEmail = null;
    private String fromPass = null;
    private EmailUtil(){}
    public EmailUtil(String toEmail){
        this.toEmail = toEmail;
    }

    public void sendEmail(Email email){}
    public void sendEmail(String fromEmail, String fromPass){}
    public void sendEmail(String fromEmail, String fromPass, String smtpHost){}
}
