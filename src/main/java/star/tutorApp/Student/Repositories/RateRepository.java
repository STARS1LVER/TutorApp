package star.tutorApp.Student.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import star.tutorApp.Student.Entities.RateEntity;

@Repository
public interface RateRepository extends JpaRepository<RateEntity, Long> {
}
