package lab13;

import java.io.Serializable;

public class Course implements Serializable{
	String courseCode;
	String courseDescription;
	
	public Course( String courseCode, String courseDescription){
		this.courseCode = courseCode;
		this.courseDescription = courseDescription;
	}
	
	// getters
	public String getCourseCode(){
		return courseCode;
	}
	public String getCourseDescription(){
		return courseDescription;
	}
	
	// setters
	public void setCourseCode(String courseCode){
		this.courseCode = courseCode;
	}
	public void setCourseDescription(String courseDescription){
		this.courseDescription = courseDescription;
	}
	
	public String toString(){
		return "Course Code: " + courseCode +
				"\nCourse Description: " + courseDescription;
	}
}
