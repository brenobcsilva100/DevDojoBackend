package br.com.devdojo.examgenerator.endpoint.v1;

import br.com.devdojo.examgenerator.persistence.model.ApplicationUser;
import br.com.devdojo.examgenerator.persistence.model.Course;
import br.com.devdojo.examgenerator.persistence.repository.CourseRepository;
import br.com.devdojo.examgenerator.util.EndpointUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/professor/course")
public class CourseEndpoint {


    private final CourseRepository courseRepository;

    private final EndpointUtil endpointUtil;

    private final CourseService courseService;
    @Autowired
    public CourseEndpoint(CourseRepository courseRepository, EndpointUtil endpointUtil, CourseService courseService) {
        this.courseRepository = courseRepository;
        this.endpointUtil = endpointUtil;
        this.courseService = courseService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getCourseById(@PathVariable long id){
        return endpointUtil.returnObjectOrNotFound(courseRepository.findOne(id));
    }

    @GetMapping(path = "list")
    public ResponseEntity<?> listCourses(@RequestParam(value = "name", defaultValue=" ") String name){
        return endpointUtil.returnObjectOrNotFound(courseRepository.listCourses(name));
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        courseService.throwResourceNotFoundIfCourseDoesNotExist(id);
        courseRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Course course){
        courseService.throwResourceNotFoundIfCourseDoesNotExist(course);
        courseRepository.save(course);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Course course){
        course.setProfessorBreno(endpointUtil.extractProfessorFromToken());

        return new ResponseEntity<>(courseRepository.save(course), HttpStatus.OK);
    }


}
