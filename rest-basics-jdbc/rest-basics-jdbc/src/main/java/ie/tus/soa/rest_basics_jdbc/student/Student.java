package ie.tus.soa.rest_basics_jdbc.student;


public class Student {
	private int id;
	private String name;
	private String birthDate;
	
	public Student() {
		super();
	}
	
	public Student(int id, String name, String birthDate) {
		super();
		this.id=id;
		this.name=name;
		this.birthDate=birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
}
