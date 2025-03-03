package ie.tus.eng.ipa_lab.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ie.tus.eng.ipa_lab.course.jpa.CourseJPARepository;

@Component
public class CourseCommandLineRunner {

	@Autowired
	private CourseJPARepository repository;
	
	@Override
	public void run(String... args) throws Exception{
		repository.save(new Course(1, "WebDev Jpa", "Enda"));
		repository.save(new Course(2, "Math Jpa", "Mark"));
		repository.save(new Course(3, "Software Jpa", "Declan"));
		repository.save(new Course(4, "Database Jpa", "Denis"));
		
		//delete row with id 2
		//add an l here for long
		repository.deleteById(2l);
		
		//add and l here for long
		System.out.println(repository.findById(4l));
		
		//print all the course
		System.out.println(repository.findAll());
	}
}
