package ie.tus.eng.course_service.course.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.tus.eng.course_service.course.Course;

public interface CourseJPARepository extends JpaRepository<Course, Long> {

}
