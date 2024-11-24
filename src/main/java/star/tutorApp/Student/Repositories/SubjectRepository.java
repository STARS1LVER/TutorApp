package star.tutorApp.Student.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import star.tutorApp.Student.Dtos.SubjectUserDto;
import star.tutorApp.Student.Entities.SubjectEntity;
import star.tutorApp.Student.Entities.SubjectUserEntity;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

    List<SubjectEntity> findBySemester(int semester);

    @Query("SELECT su FROM SubjectUserEntity su " +
            "JOIN FETCH su.subject s " +
            "JOIN FETCH su.tutor t " +
            "WHERE su.subject.id = :id " +
            "GROUP BY s.name") // Agrupando por el nombre del subject
    List<SubjectUserEntity> findBySubjectId(@Param("id") Long id);
}
