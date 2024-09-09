package org.example.Service;

import org.example.Repository.Erepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.example.Models.Klientas;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class Eservice {

    @Autowired
    private Erepository erepository;

    public String registracijosLaiskas(String vardas, String elPastas) {
        return erepository.registracijosLaiskas(vardas, elPastas);
    }

    public String uzsisakePrenumerataLaiskas(String vardas, String elPastas) {
        return erepository.uzsisakePrenumerataLaiskas(vardas, elPastas);
    }

    // CHAT GPT -----------------------------------------

    public void sendNewEventEmail(List<Klientas> subscribedUsers, String eventName, String eventType, Date eventDate, double eventPrice, String fileName, byte[] eventImage) {
        String emailHtml = generateEmailHtml(eventName, eventType, eventDate, eventPrice, fileName, eventImage);

        // Loop through all subscribed clients and send the email
        for (Klientas client : subscribedUsers) {
            String clientName = client.getVardas();
            String clientEmail = client.getElPastas();
            erepository.sendEmail(clientName, clientEmail, "New Event: " + eventName, emailHtml);
        }
    }

    // Function to generate email content with dynamic image support
    private String generateEmailHtml(String eventName, String eventType, Date eventDate, double eventPrice, String fileName, byte[] eventImage) {
        // Dynamically determine the MIME type based on the file name
        String mimeType = getMimeType(fileName);

        // Encode the image to Base64
        String base64Image = Base64.getEncoder().encodeToString(eventImage);

        // Generate the HTML content with the dynamically determined MIME type
        return "<div style='border:1px solid #ddd;padding:15px; max-width: 600px; margin: 0 auto;'>"
                + "<h3 style='font-size:18px; margin-bottom:10px;'>" + eventName + "</h3>"
                + "<p style='font-size:16px; margin-bottom:10px;'>Renginio tipas: " + eventType + "</p>"
                + "<p style='font-size:16px; margin-bottom:10px;'>Data: " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(eventDate) + "</p>"
                + "<img src='data:" + mimeType + ";base64," + base64Image + "' style='width: 100%; height: auto; max-width: 100%; display: block; margin: 0 auto;'>"
                + "<p style='font-size:16px; margin-top:10px;'>Kaina: $" + eventPrice + "</p>"
                + "</div>";
    }

    // Helper function to determine MIME type based on the file extension
    private String getMimeType(String fileName) {
        // Extract the file extension from the file name
        String fileExtension = "";

        // Find the last dot in the file name and get the extension
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            fileExtension = fileName.substring(index + 1);
        }

        // Map file extensions to MIME types
        switch (fileExtension.toLowerCase()) {
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            default:
                // If the image format is unsupported, return binary data type as a fallback
                return "application/octet-stream";
        }
    }
}