package DependencyInjectionExample;
import java.util.Scanner;

public class DependencyInjectionExample {
    public static void main(String[] args) {
        // Create repository
        CustomerRepository customerRepo = new CustomerRepositoryImpl();

        // Inject repository into service
        CustomerService customerService = new CustomerService(customerRepo);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Customer ID: ");
        String customerId = sc.nextLine();

        // Fetch and display customer details
        Customer customer = customerService.getCustomerById(customerId);
        System.out.println("Customer Details: " + customer);
    }
}