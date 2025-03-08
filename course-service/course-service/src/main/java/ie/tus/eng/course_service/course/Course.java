package ie.tus.eng.course_service.course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="course")
public class Course {
	@Id
	private long course_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="code")
	private String code;
	
	public Course() {
		super();
	}
	
	public Course(long course_id, String name, String code) {
		super();
		this.course_id = course_id;
		this.name = name;
		this.code = code;
	}

	public long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Course [course_id=" + course_id + ", name=" + name + ", code=" + code + "]";
	}
	
}