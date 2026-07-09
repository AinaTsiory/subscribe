package school.hei.subscribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<JCourse, UUID> {
}