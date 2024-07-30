import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Product {
    private int productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Quantity: " + quantity + ", Price: " + price;
    }
}

class Inventory {
    private Map<Integer, Product> inventory;

    public Inventory() {
        this.inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    public void updateProduct(int productId, String productName, int quantity, double price) {
        if (inventory.containsKey(productId)) {
            Product product = inventory.get(productId);
            product.setProductName(productName);
            product.setQuantity(quantity);
            product.setPrice(price);
        }
    }

    public void deleteProduct(int productId) {
        inventory.remove(productId);
    }

    public Product getProduct(int productId) {
        return inventory.get(productId);
    }

    public void displayInventory() {
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add product");
            System.out.println("2. Update product");
            System.out.println("3. Delete product");
            System.out.println("4. Display inventory");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = sc.nextInt();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    inventory.addProduct(new Product(id, name, quantity, price));
                    break;

                case 2:
                    System.out.print("Enter product ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new product name: ");
                    String updateName = sc.nextLine();
                    System.out.print("Enter new quantity: ");
                    int updateQuantity = sc.nextInt();
                    System.out.print("Enter new price: ");
                    double updatePrice = sc.nextDouble();
                    inventory.updateProduct(updateId, updateName, updateQuantity, updatePrice);
                    break;

                case 3:
                    System.out.print("Enter Product ID to delete: ");
                    int deleteId = sc.nextInt();
                    inventory.deleteProduct(deleteId);
                    break;

                case 4:
                    System.out.println("\nCurrent Inventory:");
                    inventory.displayInventory();
                    break;

                case 5:
                    System.out.println("Successfully exited.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}
