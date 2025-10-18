
// StudentManagementSystem.java
import java.util.*;

public class StudentManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        boolean running = true;
        
        System.out.println("=== Student Management System ===\n");
        
        while (running) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Display Top Students (CGPA > 8.0)");
            System.out.println("7. Exit");
            System.out.print("\nEnter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewAllStudents(); break;
                case 3: searchStudent(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6: displayTopStudents(); break;
                case 7: 
                    running = false;
                    System.out.println("Thank you for using the system!");
                    break;
                default: 
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        
        System.out.print("Enter department: ");
        String dept = sc.nextLine();
        
        System.out.print("Enter CGPA: ");
        double cgpa = sc.nextDouble();
        sc.nextLine();
        
        Student student = new Student(nextId++, name, email, dept, cgpa);
        students.add(student);
        
        System.out.println("\n✓ Student added successfully with ID: " + student.getId());
    }

    private static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\nNo students found!");
            return;
        }
        
        System.out.println("\n=== All Students ===");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void searchStudent() {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        Student student = findStudentById(id);
        if (student != null) {
            System.out.println("\n" + student);
        } else {
            System.out.println("\nStudent not found!");
        }
    }

    private static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("\nStudent not found!");
            return;
        }
        
        System.out.println("\nCurrent details: " + student);
        System.out.println("\nLeave blank to keep current value");
        
        System.out.print("Enter new name: ");
        String name = sc.nextLine();
        if (!name.isEmpty()) student.setName(name);
        
        System.out.print("Enter new email: ");
        String email = sc.nextLine();
        if (!email.isEmpty()) student.setEmail(email);
        
        System.out.print("Enter new department: ");
        String dept = sc.nextLine();
        if (!dept.isEmpty()) student.setDepartment(dept);
        
        System.out.print("Enter new CGPA (0 to skip): ");
        double cgpa = sc.nextDouble();
        sc.nextLine();
        if (cgpa > 0) student.setCgpa(cgpa);
        
        System.out.println("\n✓ Student updated successfully!");
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("\n✓ Student deleted successfully!");
        } else {
            System.out.println("\nStudent not found!");
        }
    }

    private static void displayTopStudents() {
        List<Student> topStudents = new ArrayList<>();
        for (Student s : students) {
            if (s.getCgpa() >= 8.0) {
                topStudents.add(s);
            }
        }
        
        if (topStudents.isEmpty()) {
            System.out.println("\nNo students with CGPA >= 8.0");
            return;
        }
        
        topStudents.sort((s1, s2) -> Double.compare(s2.getCgpa(), s1.getCgpa()));
        
        System.out.println("\n=== Top Performing Students ===");
        for (Student s : topStudents) {
            System.out.println(s);
        }
    }

    private static Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}