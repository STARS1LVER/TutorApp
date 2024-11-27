package star.tutorApp.Student.Dtos;

import org.springframework.stereotype.Component;
import star.tutorApp.Student.Entities.SubjectUserEntity;

@Component
public class SubjectUserMapper {

    public SubjectUserDto toDto(SubjectUserEntity entity) {
        return new SubjectUserDto(
                entity.getId(),
                entity.getTutor().getName(),
                entity.getTutor().getEmail(),
                entity.getSubject().getName(),
                entity.getSubject().getSemester(),
                entity.getSubject().getImgBs64(),
                entity.getSubject().getCredits(),
                entity.getSubject().getCode(),
                entity.getSubject().getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}