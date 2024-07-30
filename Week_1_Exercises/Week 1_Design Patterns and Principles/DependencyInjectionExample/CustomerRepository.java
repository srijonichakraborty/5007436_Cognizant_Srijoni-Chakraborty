package DependencyInjectionExample;

public interface CustomerRepository {
    Customer findCustomerById(String id);
}