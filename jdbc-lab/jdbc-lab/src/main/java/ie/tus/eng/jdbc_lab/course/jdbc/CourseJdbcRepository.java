package ie.tus.eng.jdbc_lab.course.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ie.tus.eng.jdbc_lab.course.Course;

//In spring, @Repository is used for classes that talk to databases
//@Repository is a specialization of @Component
@Repository
public class CourseJdbcRepository {
	
	@Autowired
	private JdbcTemplate springJdbcTemplate;
	/*
	 * The JdbcTemplate is the object/bean used to update the database
	 * Because we're using the spring-jdbc package, a JdbcTemplate bean
	 * is automatically loaded and so can be wired in.
	 */
	
	//private static String INSERT_QUERY = "INSERT INTO COURSE VALUES(1, 'Learn Spring', 'Enda')";
	private static String INSERT_QUERY = "INSERT INTO COURSE VALUES(?, ?, ?)";
	private static String DELETE_QUERY = "DELETE FROM COURSE WHERE ID = ?";
	private static String SELECT_QUERY = "SELECT * FROM COURSE WHERE ID = ?";
	private static String SELECT_ALL_QUERY = "SELECT * FROM COURSE";
	
	public void insert(Course course) {
		//update method can be used for insert, update and delete queries
		springJdbcTemplate.update(INSERT_QUERY,
				course.getId(), course.getName(), course.getAuthor()); // fill in the (?, ?, ?) above
	}
	
	public int deleteById(int id) {
		return springJdbcTemplate.update(DELETE_QUERY, id);
	}
	
	public Course findById(int id) {
		/*
		 * using queryForObject method here:
		 * we know there's just one object coming.. don't need to return a result set
		 * need BeanPropertyMapper to convert from the Result Set to a course object
		 */
		
		return springJdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class), id);
	}
	
	public List<Course> findAll(){
		//using 'queryForObject' method here:
		//need BeanPropertyMapper to convert from the Result Set to a course object
		return springJdbcTemplate.query(SELECT_ALL_QUERY, new BeanPropertyRowMapper<>(Course.class));
	}
}
