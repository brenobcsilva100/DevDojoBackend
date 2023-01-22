package br.com.devdojo.examgenerator.persistence.repository;

import br.com.devdojo.examgenerator.persistence.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c where c.id = ?1 and c.professorBreno = ?#{principal.professorBreno}")
    Course findOne(Long aLong);

    @Query("select c from Course c where c = ?1 and c.professorBreno = ?#{principal.professorBreno}")
    Course findOne(Course course);


    @Query("select c from Course c where c.name like %?1% and c.professorBreno = ?#{principal.professorBreno}")
    List<Course> listCourses(String name);

}
