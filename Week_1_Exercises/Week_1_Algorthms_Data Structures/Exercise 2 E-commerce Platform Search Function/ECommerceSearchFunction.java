import java.util.Arrays;
import java.util.Scanner;

class Product {
    private int productId;
    private String productName;
    private String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Category: " + category;
    }
}

class ECommercePlatform {
    private Product[] products;
    private int productCount;

    public ECommercePlatform(int capacity) {
        products = new Product[capacity];
        productCount = 0;
    }

    public void addProduct(Product product) {
        if (productCount < products.length) {
            products[productCount++] = product;
        }
    }

    public Product linearSearch(String productName) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getProductName().equalsIgnoreCase(productName)) {
                return products[i];
            }
        }
        return null;
    }

    public Product binarySearch(String productName) {
        Arrays.sort(products, 0, productCount, (a, b) -> a.getProductName().compareToIgnoreCase(b.getProductName()));
        int left = 0, right = productCount - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = productName.compareToIgnoreCase(products[mid].getProductName());
            if (result == 0) {
                return products[mid];
            }
            if (result > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public void displayProducts() {
        for (int i = 0; i < productCount; i++) {
            System.out.println(products[i]);
        }
    }
}

public class ECommerceSearchFunction {
    public static void main(String[] args) {
        ECommercePlatform platform = new ECommercePlatform(10);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the no. of products you want to add: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for product " + (i + 1) + ":");
            System.out.print("Product ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Product name: ");
            String name = sc.nextLine();
            System.out.print("Category: ");
            String category = sc.nextLine();

            platform.addProduct(new Product(id, name, category));
        }

        System.out.println("\nAvailable products:");
        platform.displayProducts();

        System.out.print("\nEnter product name to search: ");
        String productName = sc.nextLine();

        // Linear Search
        System.out.println("\nPerforming linear search...");
        Product foundProduct = platform.linearSearch(productName);
        if (foundProduct != null) {
            System.out.println("Product found: " + foundProduct);
        } else {
            System.out.println("Product not found");
        }

        // Binary Search
        System.out.println("\nPerforming binary search...");
        foundProduct = platform.binarySearch(productName);
        if (foundProduct != null) {
            System.out.println("Product found: " + foundProduct);
        } else {
            System.out.println("Product not found");
        }

        sc.close();
    }
}
