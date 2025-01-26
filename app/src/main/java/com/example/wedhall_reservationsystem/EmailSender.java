package com.example.wedhall_reservationsystem;

import android.os.AsyncTask;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender extends AsyncTask<Void, Void, Void> {
    private String email; // recipient's email
    private AppCompatActivity activity;

    // Constructor for EmailSender class
    public EmailSender(AppCompatActivity activity, String email) {
        this.activity = activity;
        this.email = email;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String resetLink = "http://localhost/wedding_hall/reset_password.php?email=" + email;

        // Mailtrap (or any SMTP) properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io"); // Your Mailtrap SMTP host
        props.put("mail.smtp.port", "2525"); // Your Mailtrap SMTP port

        // Authentication for Mailtrap account
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("8d4d2132e65cee", "551ac8924a8b9a"); // Replace with your Mailtrap credentials
            }
        });

        try {
            // Create an email message with HTML content
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@example.com")); // Replace with the sender's email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Password Reset Request");

            // Create HTML email content with a button
            String emailContent = "<h3>Reset Your Password</h3>" +
                    "<p>Click the button below to reset your password:</p>" +
                    "<a href=\"" + resetLink + "\" style=\"display: inline-block; padding: 10px 20px; " +
                    "background-color: #4CAF50; color: white; text-align: center; text-decoration: none; " +
                    "border-radius: 5px;\">Reset Password</a>";

            message.setContent(emailContent, "text/html"); // Set the email content type to HTML

            // Send the email
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Log any exceptions during sending
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        // Notify user that email has been sent
        Toast.makeText(activity, "Reset email sent!", Toast.LENGTH_SHORT).show();
    }
}
