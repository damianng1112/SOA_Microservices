package ie.tus.soa.student_service.course;

public class Course {
	private long course_Id;
	private String name;
	private String code;
	
	public Course() {
		super();
	}

	public Course(long course_Id, String name, String code) {
		super();
		this.course_Id = course_Id;
		this.name = name;
		this.code = code;
	}

	public long getCourse_Id() {
		return course_Id;
	}

	public void setCourse_Id(long course_Id) {
		this.course_Id = course_Id;
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
		return "Course [course_Id=" + course_Id + ", name=" + name + ", code=" + code + "]";
	}
	
	
}
