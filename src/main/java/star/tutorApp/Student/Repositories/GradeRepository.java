package star.tutorApp.Student.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import star.tutorApp.Student.Entities.GradeEntity;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, Long> {
}
