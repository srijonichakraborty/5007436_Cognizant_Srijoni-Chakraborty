import java.util.Scanner;

class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + ", Name: " + name + ", Position: " + position + ", Salary: " + salary;
    }
}

public class EmployeeManagementSystem {
    private Employee[] employees;
    private int employeeCount;

    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        employeeCount = 0;
    }

    public void addEmployee(Employee employee) {
        if (employeeCount < employees.length) {
            employees[employeeCount++] = employee;
        } else {
            System.out.println("Employee array full.");
        }
    }

    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    public void traverseEmployees() {
        for (int i = 0; i < employeeCount; i++) {
            System.out.println(employees[i]);
        }
    }

    public void deleteEmployee(int employeeId) {
        int index = -1;
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < employeeCount - 1; i++) {
                employees[i] = employees[i + 1];
            }
            employees[--employeeCount] = null;
            System.out.println("Employee deleted");
        } else {
            System.out.println("Employee not found");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the no. of employees you want to manage: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        EmployeeManagementSystem ems = new EmployeeManagementSystem(n);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add employee");
            System.out.println("2. Search employee");
            System.out.println("3. Traverse employees");
            System.out.println("4. Delete employee");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter position: ");
                    String position = sc.nextLine();
                    System.out.print("Enter salary: ");
                    double salary = sc.nextDouble();

                    ems.addEmployee(new Employee(id, name, position, salary));
                    break;

                case 2:
                    System.out.print("Enter employee ID to search: ");
                    int searchId = sc.nextInt();
                    Employee emp = ems.searchEmployee(searchId);
                    if (emp != null) {
                        System.out.println("Employee found: " + emp);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 3:
                    System.out.println("Employee records:");
                    ems.traverseEmployees();
                    break;

                case 4:
                    System.out.print("Enter employee ID to delete: ");
                    int deleteId = sc.nextInt();
                    ems.deleteEmployee(deleteId);
                    break;

                case 5:
                    System.out.println("Exited");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option, try again");
                    break;
            }
        }
    }
}
