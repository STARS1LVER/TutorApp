    package star.tutorApp.Student.Services;

    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import star.tutorApp.Student.Dtos.SubjectUserDto;
    import star.tutorApp.Student.Dtos.SubjectUserMapper;
    import star.tutorApp.Student.Entities.SubjectEntity;
    import star.tutorApp.Student.Entities.SubjectUserEntity;
    import star.tutorApp.Student.Repositories.SubjectRepository;

    import java.util.List;
    import java.util.Optional;
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

        public List<SubjectUserDto> getSubjectDetailsById(Long id) {
            List<SubjectUserEntity> data = subjectRepository.findBySubjectId(id);
            return data.stream()
                    .map(subjectUserMapper::toDto) // Transformar entidades en DTOs
                    .collect(Collectors.toList());
        }
    }
