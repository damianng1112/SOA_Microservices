package ie.tus.eng.course_service.course;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ie.tus.eng.course_service.course.jpa.CourseJPARepository;

@RestController
public class CourseResource {
	
	private CourseJPARepository repository;
	
	@Autowired
	public CourseResource(CourseJPARepository repository) {
		this.repository = repository;
	}

	// GET
	@GetMapping("/courses")
	public List<Course> retrieveAllCourses() {
		return repository.findAll();
	}

	// GET (student/1)
	@GetMapping("/courses/{id}")
	public ResponseEntity<Course> retrieveCourse(@PathVariable long id) {
		Optional<Course> course = repository.findById(id);
		
		if (course.isEmpty()) {
			System.out.println("Course not found");
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(course.get());
		}
	}
	
	//POST
	@PostMapping("/courses")
	public ResponseEntity<Course> createCourse(@RequestBody Course course) {
		Course savedCourse = repository.save(course);

		// Get the URI of the created student
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCourse.getCourse_id()).toUri();

		// return response status = 201 (created), along with the location
		return ResponseEntity.created(location).build();
	}
	
}
