package AdapterPatternExample;

import java.util.Scanner;

public class TestPaymentProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter payment gateway (PayPal, Stripe):");
        String gateway = sc.nextLine();

        System.out.println("Enter payment amount:");
        double amount = sc.nextDouble();

        PaymentProcessor processor;

        switch (gateway) {
            case "PayPal":
                processor = new PayPalAdapter(new PayPal());
                break;
            case "Stripe":
                processor = new StripeAdapter(new Stripe());
                break;
            default:
                System.out.println("Unknown payment gateway.");
                return;
        }

        processor.processPayment(amount);
    }
}
