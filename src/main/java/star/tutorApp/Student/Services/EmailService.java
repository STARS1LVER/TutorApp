package star.tutorApp.Student.Services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tutorstar12@gmail.com"); // Correo del remitente
        message.setTo(to); // Correo del destinatario
        message.setSubject(subject); // Asunto del correo
        message.setText(body); // Cuerpo del correo

        mailSender.send(message);
    }
}
