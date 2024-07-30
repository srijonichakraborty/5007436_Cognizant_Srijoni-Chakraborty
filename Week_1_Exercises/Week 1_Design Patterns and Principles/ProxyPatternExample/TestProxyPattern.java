package ProxyPatternExample;

import java.util.Scanner;

public class TestProxyPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the image file name:");
        String fileName = sc.nextLine();

        Image image = new ProxyImage(fileName);

        // Display the image for the first time
        image.display();
        System.out.println("");

        // Display the image again
        image.display();
    }
}
