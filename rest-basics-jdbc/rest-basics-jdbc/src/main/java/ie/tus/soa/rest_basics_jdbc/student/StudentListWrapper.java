package ie.tus.soa.rest_basics_jdbc.student;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class StudentListWrapper {
	@JacksonXmlElementWrapper(useWrapping = false)
	@JsonProperty("student")
	private List<Student> students;
	
	public StudentListWrapper() {
	}
	
	public StudentListWrapper(List<Student> students) {
		this.students = students;
	}
	
	public List<Student> getStudents(){
		return students;
	}
}
