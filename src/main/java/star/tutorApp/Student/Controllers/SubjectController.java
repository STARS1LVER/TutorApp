package star.tutorApp.Student.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import star.tutorApp.Student.Dtos.SubjectUserDto;
import star.tutorApp.Student.Dtos.SubjectWithTutorsDto;
import star.tutorApp.Student.Entities.SubjectEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import star.tutorApp.Student.Services.SubjectService;
import org.springframework.security.core.userdetails.UserDetails;
import star.tutorApp.User.User;

import java.util.List;

@RestController
@RequestMapping("/subject")
@CrossOrigin("http://localhost:4200/") // Permitir solicitudes desde Angular
public class SubjectController {

    private final SubjectService subjectService;

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
}
