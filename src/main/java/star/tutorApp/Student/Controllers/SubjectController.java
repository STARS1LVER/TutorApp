package star.tutorApp.Student.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import star.tutorApp.Student.Dtos.SubjectWithTutorsDto;
import star.tutorApp.Student.Entities.SubjectEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import star.tutorApp.Student.Services.EmailService;
import star.tutorApp.Student.Services.SubjectService;
import org.springframework.security.core.userdetails.UserDetails;
import star.tutorApp.User.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = {
    "http://localhost:4200",
    "https://tutor-app-front.vercel.app"
})
public class SubjectController {
    @Autowired
    private final SubjectService subjectService;
    @Autowired
    private EmailService emailService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectWithTutorsDto> getSubjectsById(@PathVariable Long id) {
        SubjectWithTutorsDto result = subjectService.getSubjectDetailsById(id);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/semester")
    public ResponseEntity<List<SubjectEntity>> getSubjectsBySemester() {
        // Obtener el usuario autenticado desde el SecurityContext
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Obtener el objeto User desde UserDetails (si tienes una implementación personalizada de UserDetails)
        User user = (User) userDetails;

        // Acceder al semestre del usuario
        String semester = user.getSemester();
        List<SubjectEntity> subjects = subjectService.getSubjectsBySemester(Integer.parseInt(semester));

        return ResponseEntity.ok(subjects);
    }
@PostMapping("/sendRequest/{id}")
public ResponseEntity<String> sendMessageRequestTutor(
    @PathVariable Long id,
    @RequestBody Map<String, String> requestBody  // Asegúrate de que el cuerpo sea un Map
) {
    String date = requestBody.get("date");
    // Obtener usuario autenticado
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = (User) userDetails;

    // Obtener los detalles de la materia usando el ID
    SubjectWithTutorsDto subjectDetails = subjectService.getSubjectDetailsById(id);

    String baseUrl = "http://localhost:8080"; // Cambia esto según tu dominio
    String acceptUrl = baseUrl + "/subject/accept/" + id + "?studentEmail=" + user.getEmail();
    String rejectUrl = baseUrl + "/subject/reject/" + id + "?studentEmail=" + user.getEmail();

    Map<String, Object> variables = new HashMap<>();
    variables.put("tutorName", user.getName());
    variables.put("subjectName", subjectDetails.getName());
    variables.put("studentName", user.getName());
    variables.put("semester", user.getSemester());
    variables.put("date", date);
    variables.put("acceptUrl", acceptUrl);
    variables.put("rejectUrl", rejectUrl);
    
    String htmlContent = processTemplate("email-template.html", variables);
    
    emailService.sendHtmlEmail(
        "diegocardenaz11@gmail.com",    
        "Solicitud de Tutoría", 
        htmlContent
    );
    
    return ResponseEntity.ok("Correo enviado exitosamente");
}

@GetMapping("/accept/{id}")
public ResponseEntity<String> acceptTutoring(@PathVariable Long id, @RequestParam String studentEmail) {
    try {
        // Obtener detalles de la materia
        SubjectWithTutorsDto subjectDetails = subjectService.getSubjectDetailsById(id);
        
        // Preparar variables para la plantilla
        Map<String, Object> variables = new HashMap<>();
        variables.put("tutorName", subjectDetails.getTutors().get(0).getName()); // Asumiendo que hay al menos un tutor
        variables.put("subjectName", subjectDetails.getName());
        
        String htmlContent = processTemplate("confirmation-email.html", variables);
        
        // Enviar correo al estudiante usando el email proporcionado
        emailService.sendHtmlEmail(
            studentEmail,
            "Tutoría Aceptada", 
            htmlContent
        );
        
        return ResponseEntity.ok("Tutoría aceptada exitosamente");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error al procesar la solicitud: " + e.getMessage());
    }
}

@GetMapping("/reject/{id}")
public ResponseEntity<String> rejectTutoring(@PathVariable Long id, @RequestParam String studentEmail) {
    try {
        // Obtener detalles de la materia
        SubjectWithTutorsDto subjectDetails = subjectService.getSubjectDetailsById(id);
        
        // Preparar variables para la plantilla
        Map<String, Object> variables = new HashMap<>();
        variables.put("studentName", studentEmail.split("@")[0]); // Extraemos el nombre del email
        variables.put("subjectName", subjectDetails.getName());
        variables.put("tutorName", subjectDetails.getTutors().get(0).getName());
        variables.put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        
        String htmlContent = processTemplate("rejection-email-template.html", variables);
        
        // Enviar correo al estudiante
        emailService.sendHtmlEmail(
            studentEmail,
            "Tutoría No Disponible", 
            htmlContent
        );
        
        return ResponseEntity.ok("Notificación de rechazo enviada exitosamente");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error al procesar la solicitud: " + e.getMessage());
    }
}

private String processTemplate(String templateName, Map<String, Object> variables) {
    try {
        // Leer el archivo de plantilla usando InputStream
        ClassPathResource resource = new ClassPathResource("templates/" + templateName);
        String template = new String(resource.getInputStream().readAllBytes());
        
        // Reemplazar variables
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            template = template.replace("${" + entry.getKey() + "}", entry.getValue().toString());
        }
        
        return template;
    } catch (IOException e) {
        throw new RuntimeException("Error al procesar la plantilla: " + e.getMessage(), e);
    }
}
}
