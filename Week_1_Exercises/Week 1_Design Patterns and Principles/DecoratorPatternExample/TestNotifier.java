package DecoratorPatternExample;

import java.util.Scanner;

public class TestNotifier {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter notification message:");
        String message = sc.nextLine();

        System.out.println("Send via (Email, SMS, Slack):");
        String channel = sc.nextLine();

        Notifier notifier = new EmailNotifier(); // Default notifier

        if (channel.contains("SMS")) {
            notifier = new SMSNotifierDecorator(notifier);
        }
        if (channel.contains("Slack")) {
            notifier = new SlackNotifierDecorator(notifier);
        }

        notifier.send(message);
    }
}
