package ie.tus.soa.student_service.student;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.tus.soa.student_service.student.model.Student;


public interface StudentRepository extends JpaRepository<Student, Integer> {

}
