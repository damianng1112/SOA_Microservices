package ie.tus.soa.rest_basics.student;

import java.net.URI;
import java.util.List;

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

@RestController
public class StudentResource {
	private StudentDao dao;

	@Autowired
	public StudentResource(StudentDao dao) {
		this.dao = dao;
	}
	
	// GET
	@GetMapping("/students")
	public StudentListWrapper retrieveAllStudents() {
		List<Student> students = dao.findAll();
		return new StudentListWrapper(students);
	}

	// GET (student/1)
	@GetMapping("/students/{id}")
	public Student retrieveStudent(@PathVariable int id) {
		Student student = dao.findById(id);
		return student;
	}

	// POST
	@PostMapping("/students")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student savedStudent = dao.save(student);

		// Get the URI of the created student
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		// return response status = 201 (created), along with the location
		return ResponseEntity.created(location).build();
	}

	// DELETE
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
		if (dao.delete(id) == true) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// DELETE
	@DeleteMapping("/students")
	public ResponseEntity<Void> deleteAllStudent() {
		dao.deleteAllStudent();
		return ResponseEntity.noContent().build();
	}
	
	//PUT
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> editStudent(@PathVariable int id, @RequestBody Student student){
		dao.editStudent(id, student);
		return ResponseEntity.ok(student);
	}
}
