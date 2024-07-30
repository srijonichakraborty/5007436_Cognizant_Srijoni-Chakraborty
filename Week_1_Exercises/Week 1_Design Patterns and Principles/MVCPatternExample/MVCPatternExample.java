package MVCPatternExample;

import java.util.Scanner;

public class MVCPatternExample {
    public static void main(String[] args) {
        // Initialize the model, view, and controller
        Student model = new Student();
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);

        // Take user input
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Student ID: ");
        controller.setStudentId(sc.nextLine());

        System.out.println("Enter Student Name: ");
        controller.setStudentName(sc.nextLine());

        System.out.println("Enter Student Grade: ");
        controller.setStudentGrade(sc.nextLine());

        // Update the view with the data from the model
        controller.updateView();
    }
}
