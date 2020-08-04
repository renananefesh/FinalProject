package com.example.project;

import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailSender extends  AsyncTask<Void, Void, Void> {

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;
    String emailHost = "smtp.gmail.com";
    String fromUser = "talyasterman";//just the id alone without @gmail.com
    String fromUserEmailPassword = "Gm55436!";

    public void send() throws AddressException,
            MessagingException {

        GmailSender GmailSender = new GmailSender();

        GmailSender.setMailServerProperties();
        GmailSender.createEmailMessage();
        GmailSender.sendEmail();
    }

    public void setMailServerProperties() {

        String emailPort = "587";//gmail's smtp port

        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");

    }

    public void createEmailMessage() throws AddressException,
            MessagingException {
        String[] toEmails = {"talyastar@gmail.com"};
        String emailSubject = "Java Email";
        String emailBody = "This is an email sent by JavaMail api.";

      mailSession = Session.getDefaultInstance(emailProperties, new GMailAuthenticator(fromUser,fromUserEmailPassword));
        emailMessage = new MimeMessage(mailSession);


        for (int i = 0; i < toEmails.length; i++) {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");//for a html email
        //emailMessage.setText(emailBody);// for a text email

    }

    public void sendEmail() throws AddressException, MessagingException {



        Transport transport = mailSession.getTransport("smtp");

        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully.");
    }


    @Override
    protected Void doInBackground(Void... voids) {

        try {
            send();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}