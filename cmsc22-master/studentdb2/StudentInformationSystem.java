package lab13;

import java.io.*;
import java.util.*;

public class StudentInformationSystem {
	static List<Student> studentDirectory = new ArrayList();

	static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		System.out.println("Choose your action: \n" + 
				"1. Register\n" + 
				"2. Get Info\n" + 
				"3. Delete a student\n" + 
				"4. Edit\n" + 
				"5. Save\n " + 
				"6. Exit\n");
		switch (sc.nextInt()) {
		case 1:
			register();
			break;
		case 2:
			getInfo();
			break;
		case 3:
			delete();
			break;
		case 4:
			edit();
			break;
		case 5:
			saveStudent();
			break;
		default:
			// do nothing
		}
	}

	// to find existing student number, if found, returns the index
	public static int finder(String studentNumber) {
		for (Student stud : studentDirectory) {
			if (stud.getStudentNumber().equals(studentNumber)) {
				return studentDirectory.indexOf(stud);
			}
		}
		return -1;
	}

	// register
	public static void register() {
		String studentNumber = "";
		String firstName;
		char middleInitial;
		String lastName;
		String course;
		int yearLevel;
		String crushName;
		String courseCode;
		String courseDescription;
		Course faveSubject = null;
		Student b;
		String tempSN;

		System.out.println("Input Student Number: ");
		tempSN = sc.next();

		// to find if student number is existing
		if (finder(tempSN) == -1) {
			studentNumber = tempSN;
		} else {
			System.out.println("Student number is already taken!");
			register();
		}

		System.out.println("Input First Name: ");
		firstName = sc.next();
		System.out.println("Input Middle Initial: ");
		middleInitial = sc.next().charAt(0);
		System.out.println("Input Last Name: ");
		lastName = sc.next();
		System.out.println("Input Course: ");
		course = sc.next();
		System.out.println("Input Year Level: ");
		yearLevel = sc.nextInt();
		System.out.println("Input Crush Name: ");
		crushName = sc.next();
		System.out.println("Input Favorite Subject: ");
		courseCode = sc.next();
		System.out.println("Input Course Description: ");
		courseDescription = sc.next();

		b = new Student(studentNumber, firstName, middleInitial, lastName, course, yearLevel, crushName, faveSubject);
		studentDirectory.add(b);
		System.out.println("Student Added!!!");
		menu();
	}

	public static void getInfo() {
		System.out.println("Enter Student Number: ");
		String tempSN = sc.next();
		
		// to find if student number is existing
		if (finder(tempSN) == -1) {
			System.out.println("Student number not found!");
		} else {
			System.out.println(studentDirectory.get(finder(tempSN)));
		}
		menu();
 
	}

	public static void delete() {
		System.out.println("Enter Student Number: ");
		String tempSN = sc.next();
		// to find if student number is existing
		if (finder(tempSN) == -1) {
			System.out.println("Student number not found!");
		} else {
			studentDirectory.remove(finder(tempSN));
			System.out.println("Student " + tempSN + " deleted!");
		}
		menu();
	}

	public static void edit() {
		System.out.println("Enter Student Number: ");
		String tempSN = sc.next();
		if (finder(tempSN) == -1) {
			System.out.println("Student number not found!");
		} else {
			mainEdit(finder(tempSN));
		}
		menu();
	}

	public static void mainEdit(int index) {
		System.out.println("Which would you like to edit?" + "\n1. First Name" + "\n2. Middle Initial"
				+ "\n3. Last Name" + "\n4. Program" + "\n5. Year Level" + "\n6. Crush Name" + "\n7. Favorite Subject");
		switch (sc.nextInt()) {
		case 1:
			System.out.println("Input first name: ");
			studentDirectory.get(index).setFirstName(sc.next());
			System.out.println("First name edited!");
			break;
		case 2:
			System.out.println("Input middle initial: ");
			studentDirectory.get(index).setMiddleInitial(sc.next().charAt(0));
			System.out.println("Middle Initial edited!");
			break;
		case 3:
			System.out.println("Input last name: ");
			studentDirectory.get(index).setLastName(sc.next());
			System.out.println("Last name edited!");
			break;
		case 4:
			System.out.println("Input program: ");
			studentDirectory.get(index).setCourse(sc.next());
			System.out.println("Program edited!");
			break;
		case 5:
			System.out.println("Input year level: ");
			studentDirectory.get(index).setYearLevel(sc.nextInt());
			System.out.println("Year level edited!");
			break;
		case 6:
			System.out.println("Input crush name: ");
			studentDirectory.get(index).setCrushName(sc.next());
			System.out.println("Crush name edited!");
			break;
		 case 7: 
			System.out.println("Input favorite subject: ");
			studentDirectory.get(index).getCourse().setCourseCode(sc.next());
			System.out.println("Input course description: ");
			studentDirectory.get(index).getCourse().setCourseDescription(sc.next());		  
			System.out.println("Favorite subject edited!"); 
			break;
		 
		default:
			// do nothing
		}
		System.out.println("Would you like to edit more?" + "Yes or No?");
		if ("Yes".equals(sc.next())) {
			mainEdit(index);
		} else {
			menu();
		}
	}

	// save
	public static void saveStudent() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fos = new FileOutputStream("C:/Users/Jaii/Documents/workspace/lab13/list.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(studentDirectory);
			System.out.println("Saved!");
			menu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			File fin = new File("C:/Users/Jaii/Documents/workspace/lab13/list.txt");
			fis = new FileInputStream(fin);
			if (fin.length() == 0) {
				System.err.println("FILE IS EMPTY");
				// System.exit(-1);
			} else {
				ois = new ObjectInputStream(fis);
				studentDirectory = (List<Student>) ois.readObject();
			}


		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		menu();
		System.out.println("Thank You! Please come again!");
	}
}