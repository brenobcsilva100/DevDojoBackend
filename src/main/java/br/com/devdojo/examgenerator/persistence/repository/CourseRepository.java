package br.com.devdojo.examgenerator.persistence.repository;

import br.com.devdojo.examgenerator.persistence.model.Course;
import br.com.devdojo.examgenerator.persistence.model.ProfessorBreno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c where c.id = ?1 and c.professorBreno = ?#{principal.professorBreno}")
    Course findByIdAndProfessorBreno(long id);

}
