
import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.Recipient;
import com.mailersend.sdk.emails.Email;
import com.mailersend.sdk.exceptions.MailerSendException;

public void sendEmail() {

    Email email = new Email();

    email.setFrom("asd", "trial-3zxk54vkoqqljy6v.mlsender.net");
    email.addRecipient("asd", "mokslaivyksta@gmail.com");

    // you can also add multiple recipients by calling addRecipient again
    email.addRecipient("name 2", "your@recipient2.com");

    // there's also a recipient object you can use
    Recipient recipient = new Recipient("name", "your@recipient3.com");
    email.addRecipient(recipient.name, recipient.email);

    email.setSubject("Tau pavyko");

    email.setPlain("Sveikinu");
    email.setHtml("This is the HTML content");

    MailerSend ms = new MailerSend();

    ms.setToken("manotestemailapi");

    try {

        MailerSendResponse response = ms.emails().send(email);
        System.out.println(response.messageId);
    } catch (MailerSendException e) {
        e.printStackTrace();
    }
}

public void main() {
}