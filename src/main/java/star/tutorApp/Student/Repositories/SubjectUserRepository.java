package star.tutorApp.Student.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import star.tutorApp.Student.Entities.SubjectUserEntity;

@Repository
public interface SubjectUserRepository extends JpaRepository<SubjectUserEntity, Long> {
}
