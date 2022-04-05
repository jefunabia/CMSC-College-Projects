package lab13;

import java.io.Serializable;

public class Student implements Serializable{
    private String studentNumber;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private String course;
    private int yearLevel;
    private String crushName;
    private Course faveSubject;

    // old
    public Student(String studentNumber, String firstName, char middleInitial, String lastName, String course, int yearLevel) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.course = course;
        this.yearLevel = yearLevel;
    }
    
    // new
    public Student(String studentNumber, String firstName, char middleInitial, String lastName, String course, int yearLevel, String crushName, Course faveSubject){
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.course = course;
        this.yearLevel = yearLevel;
        this.crushName = crushName;
        this.faveSubject = faveSubject;
    }
    
    // getters
    public String getStudentNumber() {
        return studentNumber;
    }
    public String getFirstName() {
        return firstName;
    }
    public char getMiddleInitial() {
        return middleInitial;
    }
    public String getCourse() {
        return course;
    }
    public int getYearLevel() {
        return yearLevel;
    }
    public String getCrushName(){
    	return crushName;
    }
    public Course getFaveSubject(){
    	return faveSubject;
    }
    
    // setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setMiddleInitial(char middleInitial) {
        this.middleInitial = middleInitial;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }
    public void setCrushName(String crushName){
    	this.crushName = crushName;
    }
    public void setFaveSubject(Course faveSubject){
    	//faveSubject.getCourseCode();
    	//faveSubject.getCourseDescription();
    	this.faveSubject = faveSubject;
    }
    
  
    public String toString() {
        return "Student Number: " + studentNumber +
               "\nName: " + firstName + " " + middleInitial + " " + lastName +
               "\nProgram: " + course +
               "\nYear Level: " + yearLevel +
               "\nCrush Name: " + crushName +
               "\nFavorite Subject: " + faveSubject;
    }

}