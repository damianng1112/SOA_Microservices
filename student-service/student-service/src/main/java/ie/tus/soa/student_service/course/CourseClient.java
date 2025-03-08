package ie.tus.soa.student_service.course;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-service", url = "http://localhost:8081")
public interface CourseClient {
	@GetMapping("/courses/{course_id}")
	Course getCourseById(@PathVariable long course_id);
}
