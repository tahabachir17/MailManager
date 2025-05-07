package com.emailapp;

import jakarta.mail.*;
import java.util.Properties;

public class EmailReceiver {

    public static void readInbox(String userEmail, String password) {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imap");
        props.put("mail.imap.host", "localhost");
        props.put("mail.imap.port", "143");

        try {
            Session session = Session.getDefaultInstance(props);
            Store store = session.getStore("imap");
            store.connect("localhost", userEmail, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            System.out.println("📥 Nombre de messages : " + messages.length);

            for (Message message : messages) {
                System.out.println("---------------------------");
                System.out.println("De : " + message.getFrom()[0]);
                System.out.println("Sujet : " + message.getSubject());
                System.out.println("Contenu : " + message.getContent());
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
