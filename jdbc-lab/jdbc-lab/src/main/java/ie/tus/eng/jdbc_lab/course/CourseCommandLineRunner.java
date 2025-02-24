package ie.tus.eng.jdbc_lab.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ie.tus.eng.jdbc_lab.course.jdbc.CourseJdbcRepository;

@Component
public class CourseCommandLineRunner implements CommandLineRunner{
	
	@Autowired
	private CourseJdbcRepository repository;
	
	@Override
	public void run(String... args) throws Exception{
		repository.insert(new Course(1, "SOA", "Enda")); // calls the insert method in the CourseJdbcRepository
		repository.insert(new Course(2, "Distributed System", "Peter")); // calls the insert method in the CourseJdbcRepository
		repository.insert(new Course(3, "Security", "Jacob")); // calls the insert method in the CourseJdbcRepository
		repository.insert(new Course(4, "Engineer in Society", "Sharon")); // calls the insert method in the CourseJdbcRepository
		repository.insert(new Course(5, "Engineer in Society(CA)", "Eoin")); // calls the insert method in the CourseJdbcRepository
		repository.deleteById(2);
		System.out.println(repository.findById(4));
	}
}

/*
 * This class implements CommandLineRunner, which is a class that is run when
 * the application starts. We'll use it to run the CourseJdbcRepository bean to
 * insert a row into the database.
 * 
 * Typically, data will be inserted in the database as a result of a POST/PUT
 * request, but for now we'll use CommandLineRunner to test the insert.
 */
