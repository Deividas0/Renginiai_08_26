package org.example.Repository;

import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.emails.Email;
import com.mailersend.sdk.exceptions.MailerSendException;
import org.springframework.stereotype.Repository;

@Repository
public class Erepository {

    // Use the correct and consistent token
    private static final String API_TOKEN = "mlsn.f5dede72bba316b97c5df36bf03a0b4fad9d89b69c001ecccfec9e7720117c06"; // Ensure this token is valid and used consistently

    // Method to send registration email
    public String registracijosLaiskas(String vardas, String elPastas) {
        try {
            Email email = new Email();
            email.setFrom("Renginiai", "mokslaivyksta@trial-3zxk54vkoqqljy6v.mlsender.net");
            email.addRecipient(vardas, elPastas);
            email.setSubject("Registracija");
            email.setHtml("Sveiki " + vardas + ". Džiaugiames, kad prisijungėte prie mūsų!");

            MailerSend ms = new MailerSend();
            ms.setToken(API_TOKEN); // Use the consistent token

            MailerSendResponse response = ms.emails().send(email);
            return "Email sent with Message ID: " + response.messageId;
        } catch (MailerSendException e) {
            e.printStackTrace();
            return "Failed to send registration email due to: " + e.getMessage();
        }
    }

    // Method to send subscription confirmation email
    public String uzsisakePrenumerataLaiskas(String vardas, String elPastas) {
        try {
            Email email = new Email();
            email.setFrom("Renginiai", "mokslaivyksta@trial-3zxk54vkoqqljy6v.mlsender.net");
            email.addRecipient(vardas, elPastas);
            email.setSubject("Prenumerata");
            email.setHtml("Prenumerata sėkmingai aktyvuota. Nuo šiol gausite visas naujienas apie visus būsimus renginius.");

            MailerSend ms = new MailerSend();
            ms.setToken(API_TOKEN); // Use the consistent token

            MailerSendResponse response = ms.emails().send(email);
            return "Email sent with Message ID: " + response.messageId;
        } catch (MailerSendException e) {
            e.printStackTrace();
            return "Failed to send subscription email due to: " + e.getMessage();
        }
    }


    public String sendEmail(String recipientName, String recipientEmail, String subject, String htmlContent) {
        try {
            Email email = new Email();
            email.setFrom("Renginiai", "mokslaivyksta@trial-3zxk54vkoqqljy6v.mlsender.net");
            email.addRecipient(recipientName, recipientEmail); // Include recipient's name
            email.setSubject(subject);
            email.setHtml(htmlContent);

            MailerSend ms = new MailerSend();
            ms.setToken(API_TOKEN); // Use the consistent token

            MailerSendResponse response = ms.emails().send(email);
            return "Email sent with Message ID: " + response.messageId;
        } catch (MailerSendException e) {
            e.printStackTrace();
            return "Failed to send email due to: " + e.getMessage();
        }
    }
}
