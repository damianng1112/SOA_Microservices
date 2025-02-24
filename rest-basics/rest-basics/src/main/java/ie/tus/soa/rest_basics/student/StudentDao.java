package ie.tus.soa.rest_basics.student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class StudentDao {
	// using a Map to save student - we'll change to a DB later...
	private Map<Integer, Student> studentsMap = new HashMap<Integer, Student>();

	public StudentDao() {
		// Create the following student when object is being created
		Student stud1 = new Student(1, "Joe Bloggs", LocalDate.now().minusYears(20));
		Student stud2 = new Student(2, "Anne Bloggs", LocalDate.now().minusYears(25));
		Student stud3 = new Student(3, "Billy Bloggs", LocalDate.now().minusYears(30));
		studentsMap.put(1, stud1);
		studentsMap.put(2, stud2);
		studentsMap.put(3, stud3);
	}

	// return all the student in the map
	public List<Student> findAll() {
		List<Student> students = new ArrayList<Student>();
		students.addAll(studentsMap.values());
		return students;
	}

	// return one student, given the id
	public Student findById(int id) {
		return studentsMap.get(id);
	}

	// save a student to the Map
	public Student save(Student student) {
		student.setId(getNextId());
		studentsMap.put(student.getId(), student);
		return student;
	}

	// get the next id to assign to new student
	public int getNextId() {
		return studentsMap.size() + 1;
	}

	// delete the student based on the id
	public boolean delete(int id) {
		if (studentsMap.remove(id) != null) {
			return true;
		} else {
			return false;
		}
	}

	// delete all student
	public void deleteAllStudent() {
		studentsMap.clear();
	}

	// edit student
	public Student editStudent(int id, Student student) {
		studentsMap.replace(id, student);
		return student;
	}
}
