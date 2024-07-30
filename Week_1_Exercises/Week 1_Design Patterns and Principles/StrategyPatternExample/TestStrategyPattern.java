package StrategyPatternExample;

import java.util.Scanner;

public class TestStrategyPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PaymentContext paymentContext = new PaymentContext();

        System.out.println("Choose payment method: 1. Credit Card 2. PayPal");
        int choice = sc.nextInt();
        sc.nextLine();  // Consume newline

        if (choice == 1) {
            System.out.println("Enter card number:");
            String cardNumber = sc.nextLine();
            System.out.println("Enter card holder name:");
            String cardHolder = sc.nextLine();
            System.out.println("Enter expiry date (MM/YY):");
            String expiryDate = sc.nextLine();
            System.out.println("Enter CVV:");
            String cvv = sc.nextLine();

            paymentContext.setPaymentStrategy(new CreditCardPayment(cardNumber, cardHolder, expiryDate, cvv));
        } else if (choice == 2) {
            System.out.println("Enter PayPal email:");
            String email = sc.nextLine();
            System.out.println("Enter PayPal password:");
            String password = sc.nextLine();

            paymentContext.setPaymentStrategy(new PayPalPayment(email, password));
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("Enter amount to pay:");
        double amount = sc.nextDouble();

        paymentContext.pay(amount);
    }
}