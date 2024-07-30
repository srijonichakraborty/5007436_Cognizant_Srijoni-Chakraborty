package CommandPatternExample;

import java.util.Scanner;

public class TestCommandPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Light livingRoomLight = new Light();
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        RemoteControl remote = new RemoteControl();

        System.out.println("Enter command: 1 to turn ON, 2 to turn OFF");
        int choice = sc.nextInt();

        if (choice == 1) {
            remote.setCommand(lightOn);
        } else if (choice == 2) {
            remote.setCommand(lightOff);
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        remote.pressButton();
    }
}