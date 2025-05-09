package com.mail;

import java.util.Properties;

import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;

public class EmailReceiverLocalServer {
    public static Message[] receiveEmails(String user, String password) {
        try {
            Properties props = new Properties();
            props.put("mail.store.protocol", "pop3");
            props.put("mail.pop3.host", "localhost");
            props.put("mail.pop3.port", "110");

            Session session = Session.getDefaultInstance(props);
            Store store = session.getStore("pop3");
            store.connect("localhost", user, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            System.out.println("Received " + messages.length + " emails.");
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
            return new Message[0];
        }
    }
}


