import java.util.Scanner;

class Task {
    int taskId;
    String taskName;
    String status;
    Task next;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }
}

class TaskManagementSystem {
    private Task head;

    public TaskManagementSystem() {
        this.head = null;
    }

    public void addTask(int taskId, String taskName, String status) {
        Task newTask = new Task(taskId, taskName, status);
        if (head == null) {
            head = newTask;
        } else {
            Task current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTask;
        }
    }

    public Task searchTask(int taskId) {
        Task current = head;
        while (current != null) {
            if (current.taskId == taskId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void traverseTasks() {
        Task current = head;
        while (current != null) {
            System.out.println("Task ID: " + current.taskId + ", Name: " + current.taskName + ", Status: " + current.status);
            current = current.next;
        }
    }

    public void deleteTask(int taskId) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }
        if (head.taskId == taskId) {
            head = head.next;
            System.out.println("Task deleted.");
            return;
        }
        Task current = head;
        while (current.next != null && current.next.taskId != taskId) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("Task not found.");
        } else {
            current.next = current.next.next;
            System.out.println("Task deleted.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManagementSystem taskList = new TaskManagementSystem();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add task");
            System.out.println("2. Search task");
            System.out.println("3. Traverse tasks");
            System.out.println("4. Delete task");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter task ID: ");
                    int taskId = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter task name: ");
                    String taskName = sc.nextLine();
                    System.out.print("Enter status: ");
                    String status = sc.nextLine();
                    taskList.addTask(taskId, taskName, status);
                    break;
                case 2:
                    System.out.print("Enter task ID to search: ");
                    int searchId = sc.nextInt();
                    Task task = taskList.searchTask(searchId);
                    if (task != null) {
                        System.out.println("Task found: " + "Task ID: " + task.taskId + ", Name: " + task.taskName + ", Status: " + task.status);
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case 3:
                    System.out.println("Task list:");
                    taskList.traverseTasks();
                    break;
                case 4:
                    System.out.print("Enter task ID to delete: ");
                    int deleteId = sc.nextInt();
                    taskList.deleteTask(deleteId);
                    break;
                case 5:
                    System.out.println("Exited");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option, try again");
            }
        }
    }
}
