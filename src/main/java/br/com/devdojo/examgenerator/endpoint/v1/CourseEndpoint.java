package br.com.devdojo.examgenerator.endpoint.v1;

import br.com.devdojo.examgenerator.persistence.model.ApplicationUser;
import br.com.devdojo.examgenerator.persistence.model.ProfessorBreno;
import br.com.devdojo.examgenerator.persistence.repository.CourseRepository;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/professor/course")
public class CourseEndpoint {

    @Autowired
    private final CourseRepository courseRepository;

    public CourseEndpoint(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getCourseByPor(@PathVariable long id, Authentication authentication){
//        ProfessorBreno professorBreno = ((ApplicationUser) authentication.getPrincipal()).getProfessorBreno();
        return new ResponseEntity<>(courseRepository.findByIdAndProfessorBreno(id) , HttpStatus.OK);
    }
}
