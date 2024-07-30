import java.util.Scanner;

class Order {
    private int orderId;
    private String customerName;
    private double totalPrice;

    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Customer Name: " + customerName + ", Total Price: " + totalPrice;
    }
}

public class SortingCustomerOrders {

    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter no. of orders: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        Order[] orders = new Order[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for order " + (i + 1) + ":");
            System.out.print("Order ID: ");
            int orderId = sc.nextInt();
            sc.nextLine();
            System.out.print("Customer Name: ");
            String customerName = sc.nextLine();
            System.out.print("Total Price: ");
            double totalPrice = sc.nextDouble();
            orders[i] = new Order(orderId, customerName, totalPrice);
        }

        System.out.println("\nOrders before sorting:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Bubble Sort
        bubbleSort(orders);
        System.out.println("\nOrders after Bubble Sort:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Quick Sort
        quickSort(orders, 0, orders.length - 1);
        System.out.println("\nOrders after Quick Sort:");
        for (Order order : orders) {
            System.out.println(order);
        }

        sc.close();
    }
}
