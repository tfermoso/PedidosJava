package com.mycompany.pedidos.Config;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author usuario
 */
public class Mail {

    public static void sendMail() {
        //provide recipient's email ID
        String to = "tomas.fermoso@gmail.com";

        //provide sender's email ID
        String from = "bueuttp@gmail.com";
        //provide Mailtrap's username
        final String username = "bueuttp@gmail.com";
        //provide Mailtrap's password
        final String password = "atomarporsaco69";

        //provide Mailtrap's host address
        String host = "smtp.gmail.com";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        //create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);

            //set From email field
            message.setFrom(new InternetAddress(from));

            //set To email field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            //set email subject field
            message.setSubject("Here comes Jakarta Mail!");

            //set the content of the email message
            message.setText("Just discovered that Jakarta Mail is fun and easy to use");

            //send the email message
            Transport.send(message);

            System.out.println("Email Message Sent Successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
