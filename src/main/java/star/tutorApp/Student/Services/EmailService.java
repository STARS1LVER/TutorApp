package star.tutorApp.Student.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service // Esto hace que Spring registre el servicio como un bean gestionado
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(Stri  ng to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tutorstar12@gmail.com"); // Correo del remitente
        message.setTo(to); // Correo del destinatario
        message.setSubject(subject); // Asunto del correo
        message.setText(body); // Cuerpo del correo

        mailSender.send(message);
    }
}
