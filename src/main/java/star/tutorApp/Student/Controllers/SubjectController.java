package star.tutorApp.Student.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import star.tutorApp.Student.Dtos.SubjectUserDto;
import star.tutorApp.Student.Entities.SubjectEntity;
import star.tutorApp.Student.Entities.SubjectUserEntity;
import star.tutorApp.Student.Services.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/subject")
@CrossOrigin("http://localhost:4200/") // Permitir solicitudes desde Angular
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/semester/{semester}")
    public ResponseEntity<List<SubjectEntity>> getSubjectsBySemester(@PathVariable int semester) {

        List<SubjectEntity> subjects = subjectService.getSubjectsBySemester(semester);

        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<SubjectUserDto>> getSubjectsById(@PathVariable Long id) {
        List<SubjectUserDto> results = subjectService.getSubjectDetailsById(id);
        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(results);
    }
}
