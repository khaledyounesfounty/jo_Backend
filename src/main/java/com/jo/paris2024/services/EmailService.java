package com.jo.paris2024.services;

import com.jo.paris2024.DTO.BilletDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendBilletConfirmationEmail(BilletDto billet, String toEmail)
    {
        try{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        helper.setFrom("Khaledyounesfounty@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject("Confirmation de paiement et les détails de votre billet");

        String mailContent = buildEmailContent(billet);
        helper.setText(mailContent, true); // Set to 'true' to send HTML

        mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error sending email: " + e.getMessage());
        }
    }

    private String buildEmailContent(BilletDto billet) {
        String qrCodeUrl = "http://yourdomain.com/path-to-images/" + billet.getQrcode().getQrImage(); // Adjust the path as needed

        StringBuilder content = new StringBuilder();
        content.append("<h1>Cher(e) ").append(billet.getIdUtilisateur().getUtilisateurprincipal().getPrenom())
                .append(" ").append(billet.getIdUtilisateur().getUtilisateurprincipal().getNom()).append(",</h1>");
        content.append("<p>Merci pour votre achat. Votre paiement a été confirmé.</p>");
        content.append("<h2>Détails du Billet:</h2>");
        content.append("<p><strong>Événement:</strong> ").append(billet.getReservation().getIdEvent().getTitre()).append("</p>");
        content.append("<p><strong>Date:</strong> ").append(billet.getReservation().getIdEvent().getDateEvent()).append("</p>");
        content.append("<p><strong>Prix:</strong> €").append(billet.getReservation().getPrix()).append("</p>");
        content.append("<p><img src='").append(qrCodeUrl).append("' alt='QR Code' style='width:150px;height:150px;'/></p>");
        content.append("<p>Veuillez apporter le code QR à l'événement pour entrer.</p>");
        content.append("<p>Cordialement,</p>");
        content.append("<p>Équipe Paris 2024</p>");

        return content.toString();
    }
}
