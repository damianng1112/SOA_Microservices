package ie.tus.eng.ipa_lab.course.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.tus.eng.ipa_lab.course.Course;

public interface CourseJPARepository extends JpaRepository<Course, Long> {

}
