package ie.tus.soa.spring_boot_intro;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

	@GetMapping("/courses")
	public List<Course> retrieveAllCourses(){
		return Arrays.asList(
				new Course(1, "Databases", "Denis"),
				new Course(2, "Software Dev", "Declan"),
				new Course(3, "Maths", "Mark")
				);
				
	}
}
