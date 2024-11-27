package star.tutorApp.Student.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import star.tutorApp.Student.Entities.SubjectEntity;

import java.util.List;
import java.util.Map;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

    List<SubjectEntity> findBySemester(int semester);

    @Query(
            value = """
            SELECT 
                s.*,
                JSON_ARRAYAGG(JSON_OBJECT('id', u.id,'name', u.name, 'email', u.email)) AS tutors
            FROM 
                subject s
            LEFT JOIN 
                subject_user su ON s.id = su.subject_id
            LEFT JOIN 
                users u ON su.tutor_id = u.id
            WHERE 
                s.id = :subjectId
            GROUP BY 
                s.id
        """,
            nativeQuery = true
    )
    Map<String, Object> findSubjectWithTutors(@Param("subjectId") Long subjectId);
}
