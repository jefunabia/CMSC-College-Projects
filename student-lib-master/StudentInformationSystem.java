
package lab10;

import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;
import java.lang.*;

public class StudentInformationSystem {
    static List<Student> studentDirectory = new ArrayList();

    static Scanner sc = new Scanner(System.in);    
    
    public static void menu() {
        System.out.println("Choose your action: \n"
                + "1. Register\n"
                + "2. Get Info\n"
                + "3. Delete a student\n"
                + "4. Edit\n"
                + "5. Exit\n");
        switch(sc.nextInt()) {
            case 1: register();
                    break;
            case 2: getInfo();
                    break;
            case 3: delete();
                    break;
            case 4: edit();
                    break;
            default: 
            	//do nothing
        }
    }
    
    // to find existing student number, if found, returns the index
    public static int finder(String studentNumber) {
        for(Student stud: studentDirectory) {
            if(stud.getStudentNumber().equals(studentNumber)) {
                return studentDirectory.indexOf(stud);
            }
        }
        return -1;
    }
    
    public static void register() {
        String studentNumber = "";
        String firstName;
        char middleInitial;
        String lastName;
        String course;
        int yearLevel;
        
        Student b;
        String tempSN;
        
        System.out.println("Input Student Number: ");
        tempSN = sc.next();
        
        // to find if student number is existing
        if(finder(tempSN) == -1) {
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
            
        b = new Student(studentNumber, firstName, middleInitial, lastName, course, yearLevel);
        studentDirectory.add(b);
        System.out.println("Student Added!!!");
        menu();
        
    }
    
    public static void getInfo() {
        System.out.println("Enter Student Number: ");
        String tempSN = sc.next();
     // to find if student number is existing
        if(finder(tempSN) == -1) {
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
        if(finder(tempSN) == -1) {
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
        if(finder(tempSN) == -1) {
            System.out.println("Student number not found!");
        } else {
            mainEdit(finder(tempSN));
        }
        menu();
    }
    
    public static void mainEdit(int index) {
        System.out.println("Which would you like to edit?"
                + "\n1. First Name"
                + "\n2. Middle Initial"
                + "\n3. Last Name"
                + "\n4. Program"
                + "\n5. Year Level");
        switch(sc.nextInt()) {
            case 1: System.out.println("Input first name: ");
                    studentDirectory.get(index).setFirstName(sc.next());
                    System.out.println("First name edited!");
                    break;
            case 2: System.out.println("Input middle initial: ");
                    studentDirectory.get(index).setMiddleInitial(sc.next().charAt(0));
                    System.out.println("Middle Initial edited!");
                    break;
            case 3: System.out.println("Input last name: ");
                    studentDirectory.get(index).setLastName(sc.next());
                    System.out.println("Last name edited!");
                    break;
            case 4: System.out.println("Input program: ");
                    studentDirectory.get(index).setCourse(sc.next());
                    System.out.println("Program edited!");
                    break;
            case 5: System.out.println("Input year level: ");
                    studentDirectory.get(index).setYearLevel(sc.nextInt());
                    System.out.println("Year level edited!");
                    break;
            default:
            	//do nothing
        }
        System.out.println("Would you like to edit more?"
                    + "yes or no?");
        if("yes".equals(sc.next())) {
            mainEdit(index);
        }
        else {
            menu();
        }
    }
    
    public static void main(String[] args) throws IOException {
        String studentNumber = "";
        String firstName = "";
        char middleInitial = 0;
        String lastName = "";
        String course = "";
        int yearLevel;
        
        BufferedReader fr = null;
        BufferedWriter fw = null;
        
        try {
            String currentLine;
            Student a;
            int ctr = 0;
            
            fr = new BufferedReader(new FileReader("list.txt"));
            
            while((currentLine = fr.readLine()) != null && ctr < 6) {
                if(ctr == 0) {
                    studentNumber = currentLine;
                    ctr++;
                }
                else if(ctr == 1) {
                    firstName = currentLine;
                    ctr++;
                }
                else if(ctr == 2) {
                    middleInitial = currentLine.charAt(0);
                    ctr++;
                }
                else if(ctr == 3) {
                    lastName = currentLine;
                    ctr++;
                }
                else if(ctr == 4) {
                    course = currentLine;
                    ctr++;
                }
                else if(ctr == 5) {
                    yearLevel = parseInt(currentLine);
                    a = new Student(studentNumber, firstName, middleInitial, lastName, course, yearLevel);
                    studentDirectory.add(a);
                    ctr = 0;
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) fr.close();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        
        menu();
        try {
            fw = new BufferedWriter(new FileWriter("list.txt"));
            int ctr = 0;
            
            for(Student student: studentDirectory) {
                fw.write(student.getStudentNumber());
                fw.newLine();
                fw.write(student.getFirstName());
                fw.newLine();
                fw.write(student.getMiddleInitial());
                fw.newLine();
                fw.write(student.getLastName());
                fw.newLine();
                fw.write(student.getCourse());
                fw.newLine();
                fw.write("" + student.getYearLevel());
                fw.newLine();
            }
            fw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        System.out.println("Thank You! Please come again!");    
    }
}

