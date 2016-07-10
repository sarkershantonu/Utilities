package org.automation.utils.email;

/**
 * Created by shantonu on 7/10/16.
 * email sending using java
 * todo
 */
public class EmailUtil {

    private String toEmail = null;
    private String fromEmail = null;
    private String fromPass = null;
    public EmailUtil(){}
    public EmailUtil(String toEmail){
        this.toEmail = toEmail;
    }
    public void sendEmail(){}
    public void sendEmail(String fromEmail, String fromPass){}
    public void sendEmail(String fromEmail, String fromPass, String smtpHost){}
}
