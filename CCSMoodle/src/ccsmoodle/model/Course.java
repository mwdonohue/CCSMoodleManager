package ccsmoodle.model;

public class Course {
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	private String teacher;
	public Course (String name, String teacher) {
		this.name = name;
		this.teacher = teacher;
	}
}
