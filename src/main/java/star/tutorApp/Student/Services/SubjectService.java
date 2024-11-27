    package star.tutorApp.Student.Services;

    import com.fasterxml.jackson.core.JsonProcessingException;
    import com.fasterxml.jackson.core.type.TypeReference;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import star.tutorApp.Student.Dtos.SubjectUserMapper;
    import star.tutorApp.Student.Dtos.SubjectWithTutorsDto;
    import star.tutorApp.Student.Dtos.TutorDto;
    import star.tutorApp.Student.Entities.SubjectEntity;
    import star.tutorApp.Student.Entities.SubjectUserEntity;
    import star.tutorApp.Student.Repositories.SubjectRepository;

    import java.util.List;
    import java.util.Map;
    import java.util.stream.Collectors;

    @Service
    @RequiredArgsConstructor
    public class SubjectService {

        @Autowired
        private SubjectRepository subjectRepository;

        private final SubjectUserMapper subjectUserMapper;


        public List<SubjectEntity> getSubjectsBySemester(int semester) {
            return subjectRepository.findBySemester(semester);
        }

        public SubjectWithTutorsDto getSubjectDetailsById(Long id) {
            Map<String, Object> result = subjectRepository.findSubjectWithTutors(id);


            // Manejo del tipo del ID para convertir Integer a Long si es necesario
            Object idObject = result.get("id");
            Long subjectId = (idObject instanceof Integer) ? ((Integer) idObject).longValue() : (Long) idObject;

            // Mapear el resultado a un DTO
            return new SubjectWithTutorsDto(
                subjectId,
                (String) result.get("name"),
                (Integer) result.get("semester"),
                (Integer) result.get("credits"),
                parseTutors((String) result.get("tutors")),
                (Integer) result.get("code"),
                (String) result.get("description"),
                (String) result.get("img_bs64") // Transformar JSON a lista de DTOs
            );
        }

        private List<TutorDto> parseTutors(String tutorsJson) {
            System.out.println("JSON recibido: " + tutorsJson); // DEBUG: imprime el JSON

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.readValue(tutorsJson, new TypeReference<List<TutorDto>>() {});
            } catch (JsonProcessingException e) {
                System.err.println("Error al procesar JSON: " + e.getMessage());
                throw new RuntimeException("Error parsing tutors JSON", e);
            }
        }
    }
