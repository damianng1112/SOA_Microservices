package ie.tus.soa.rest_basics_jdbc.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentJdbcResource {
	
	@Autowired
	private JdbcTemplate springJdbcTemplate;
	
	private static String INSERT_QUERY = "INSERT INTO STUDENT VALUES(?, ?, ?)";
	private static String DELETE_QUERY = "DELETE FROM STUDENT WHERE ID = ?";
	private static String SELECT_QUERY = "SELECT * FROM STUDENT WHERE ID = ?";
	private static String SELECT_ALL_QUERY = "SELECT * FROM STUDENT";
	private static String UPDATE_QUERY = "UPDATE STUDENT SET NAME = ?, BIRTHDATE = ? WHERE ID = ?";

	// return all the student
	public List<Student> findAll() {
		return springJdbcTemplate.query(SELECT_ALL_QUERY, new BeanPropertyRowMapper<>(Student.class));
	}

	// return one student, given the id
	public Student findById(int id) {
		return springJdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Student.class), id);
	}

	// insert a student
	public Student insert(Student student) {
		springJdbcTemplate.update(INSERT_QUERY, student.getId(), student.getName(), student.getBirthDate());
		return student;
	}

	// delete the student based on the id
	public void delete(int id) {
		springJdbcTemplate.update(DELETE_QUERY, id);
	}

	// edit student
	public void editStudent(int id, Student student) {
		springJdbcTemplate.update(UPDATE_QUERY, student.getName(), student.getBirthDate(), id);
	}
}
