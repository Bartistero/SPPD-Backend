package pl.pollub.sppd.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.sppd.model.faculty.DegreeCourse;

public interface DegreeCourseRepository extends JpaRepository<DegreeCourse, Long> {
}
