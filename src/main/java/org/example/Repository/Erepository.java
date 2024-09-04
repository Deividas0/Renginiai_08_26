package org.example.Repository;

import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.emails.Email;
import com.mailersend.sdk.exceptions.MailerSendException;

public class Erepository {

    public String registracijosLaiskas(String vardas, String elPastas) {
        try {
            Email email = new Email();
            email.setFrom("Renginiai", "mokslaivyksta@trial-3zxk54vkoqqljy6v.mlsender.net");
            email.addRecipient(vardas, elPastas);
            email.setSubject("Registracija");
//            email.setPlain("");
            email.setHtml("Sveiki " + vardas + ". Džiaugiames, kad prisijungėte prie mūsų!");

            MailerSend ms = new MailerSend();
            ms.setToken("mlsn.f5dede72bba316b97c5df36bf03a0b4fad9d89b69c001ecccfec9e7720117c06");

            MailerSendResponse response = ms.emails().send(email);
            return "Email sent with Message ID: " + response.messageId;
        } catch (MailerSendException e) {
            e.printStackTrace();
            return "Failed to send email.";
        }
    }

    public String uzsisakePrenumerataLaiskas(String vardas, String elPastas) {

        try {
            Email email = new Email();
            email.setFrom("Renginiai", "mokslaivyksta@trial-3zxk54vkoqqljy6v.mlsender.net");
            email.addRecipient(vardas, elPastas);
            email.setSubject("Prenumerata");
//            email.setPlain("");
            email.setHtml("Prenumerata sėkmingai aktyvuota. Nuo šiol gausite visas naujienas apie" +
                    " visus būsimus renginius.");

            MailerSend ms = new MailerSend();
            ms.setToken("mlsn.f5dede72bba316b97c5df36bf03a0b4fad9d89b69c001ecccfec9e7720117c06");

            MailerSendResponse response = ms.emails().send(email);
            return "Email sent with Message ID: " + response.messageId;
        } catch (MailerSendException e) {
            e.printStackTrace();
            return "Failed to send email.";
        }
    }
}

