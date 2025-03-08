package ie.tus.soa.student_service.student;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ie.tus.soa.student_service.course.Course;
import ie.tus.soa.student_service.course.CourseClient;
import ie.tus.soa.student_service.student.model.Student;
import ie.tus.soa.student_service.student.model.StudentResponse;

@RestController
public class StudentResource {
	private StudentRepository repository;
	private CourseClient courseClient;

	@Autowired
	public StudentResource(StudentRepository repository, CourseClient courseClient) {
		this.repository = repository;
		this.courseClient = courseClient;
	}
	
	// GET (student/1)
	@GetMapping("/students/{id}")
	public ResponseEntity<StudentResponse> retrieveStudent(@PathVariable int id) {
		Optional<Student> student = repository.findById(id);
		if (student.isEmpty()) {
			System.out.println("Student not found in database");
			return ResponseEntity.notFound().build();
		}else {
			Course course = courseClient.getCourseById(student.get().getCourse_id());
			StudentResponse studentResponse = new StudentResponse(student.get(), course);
			return ResponseEntity.ok(studentResponse);
		}
	}
	
	// GET (students)
		@GetMapping("/students")
		public List<Student> retrieveAllStudent() {
			return repository.findAll();
		}

	// POST
	@PostMapping("/students")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student savedStudent = repository.save(student);

		// Get the URI of the created student
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		// return response status = 201 (created), along with the location
		return ResponseEntity.created(location).build();
	}
}
