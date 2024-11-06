package Project_SpringRestFul.repository;

import Project_SpringRestFul.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
