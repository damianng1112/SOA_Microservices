package ie.tus.soap.student_management.soap;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ie.tus.students.GetStudentDetailsResponse;
import ie.tus.soap.student_management.soap.bean.Student;
import ie.tus.soap.student_management.soap.data.StudentDao;
import ie.tus.students.DeleteStudentDetailsRequest;
import ie.tus.students.DeleteStudentDetailsResponse;
import ie.tus.students.GetAllStudentDetailsRequest;
import ie.tus.students.GetAllStudentDetailsResponse;
import ie.tus.students.GetStudentDetailsRequest;
import ie.tus.students.StudentDetails;

@Endpoint
public class StudentDetailsEndpoint {
	
	//convert incoming XML to Java
	@PayloadRoot(namespace = "http://tus.ie/students", localPart = "GetStudentDetailsRequest")
	@ResponsePayload  //converts java response to XML
	public GetStudentDetailsResponse processStudentDetailsRequest(@RequestPayload GetStudentDetailsRequest request) {
		
		GetStudentDetailsResponse response = new GetStudentDetailsResponse();
		
		/*Commented out to use StudentDao instead..
		 * 
		 * studentDetails.setId(request.getId()); studentDetails.setName("Billy");
		 * studentDetails.setAddress("Athlone");
		 */
		Student student = StudentDao.instance.getStudent(request.getId());
		StudentDetails studentDetails = new StudentDetails();
		
		studentDetails.setId(student.getId());
		studentDetails.setName(student.getName());
		studentDetails.setAddress(student.getAddress());
		
		response.setStudentDetails(studentDetails);
		
		return response;
	}
	
	@PayloadRoot(namespace = "http://tus.ie/students", localPart = "GetAllStudentDetailsRequest")
	@ResponsePayload  //converts java response to XML
	public GetAllStudentDetailsResponse processAllStudentDetailsRequest(@RequestPayload GetAllStudentDetailsRequest request) {
		GetAllStudentDetailsResponse response = new GetAllStudentDetailsResponse();
		
		//Get a student using the new Data Access object
		List<Student> students = StudentDao.instance.getAllStudents();
		
		for(Student stud : students) {
			StudentDetails studentDetails = new StudentDetails();
			studentDetails.setId(stud.getId());
			studentDetails.setName(stud.getName());
			studentDetails.setAddress(stud.getAddress());
			
			response.getStudentDetails().add(studentDetails);
		}

		return response;
	}
	
	@PayloadRoot(namespace = "http://tus.ie/students", localPart = "DeleteStudentDetailsRequest")
	@ResponsePayload  //converts java response to XML
	public DeleteStudentDetailsResponse processDeleteStudentDetailsRequest(@RequestPayload DeleteStudentDetailsRequest request) {
		//Delete student based on their id
		DeleteStudentDetailsResponse response = new DeleteStudentDetailsResponse();
		
		StudentDao.instance.deleteStudent(request.getId());
				
		response.getStatus();
		
		return response;
	}
}
