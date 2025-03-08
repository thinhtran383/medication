package com.example.medicine.service;

import com.example.medicine.util.LoadEnv;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.mail.*;
import javax.mail.internet.*;

public class MailService {

    private final String username = LoadEnv.get("EMAIL");
    private final String password = LoadEnv.get("EMAIL_PASSWORD");
    private final Properties prop;
    private final ExecutorService executor;

    public MailService(String host) {
        prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", 587);
        prop.put("mail.smtp.ssl.trust", host);

        this.executor = Executors.newSingleThreadExecutor();
    }

    public void sendMailWithTable(Map<String, String> warningMedications, String subject, String recipientEmail) {
        String from = username;
        executor.submit(() -> {
            try {
                Session session = Session.getInstance(prop, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

                StringBuilder tableContent = new StringBuilder();
                tableContent.append("<table style='width: 100%; border-collapse: collapse;'>")
                        .append("<thead style='background-color: #f8f9fa;'>")
                        .append("<tr>")
                        .append("<th style='border: 1px solid #dee2e6; padding: 8px; text-align: left;'>Medicine Name</th>")
                        .append("<th style='border: 1px solid #dee2e6; padding: 8px; text-align: left;'>Status</th>")
                        .append("</tr>")
                        .append("</thead>")
                        .append("<tbody>");

                for (Map.Entry<String, String> entry : warningMedications.entrySet()) {
                    tableContent.append("<tr>")
                            .append("<td style='border: 1px solid #dee2e6; padding: 8px;'>").append(entry.getKey()).append("</td>")
                            .append("<td style='border: 1px solid #dee2e6; padding: 8px;'>").append(entry.getValue()).append("</td>")
                            .append("</tr>");
                }

                tableContent.append("</tbody></table>");

                String emailContent = "<!DOCTYPE html>" +
                        "<html lang='en'>" +
                        "<head>" +
                        "<meta charset='UTF-8'>" +
                        "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                        "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>" +
                        "<title>Medication Warning</title>" +
                        "</head>" +
                        "<body>" +
                        "<div class='container'>" +
                        "<h3 style='margin-bottom: 20px;'>Medication Warning</h3>" +
                        tableContent.toString() +
                        "</div>" +
                        "</body>" +
                        "</html>";

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
                message.setSubject(subject);

                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(emailContent, "text/html; charset=utf-8");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);

                message.setContent(multipart);

                Transport.send(message);
                System.out.println("Email sent successfully to " + recipientEmail);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }

  
}
