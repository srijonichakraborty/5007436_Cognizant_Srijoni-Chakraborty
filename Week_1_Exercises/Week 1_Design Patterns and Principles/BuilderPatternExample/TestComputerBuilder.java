package BuilderPatternExample;

import java.util.Scanner;

public class TestComputerBuilder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter CPU:");
        String CPU = sc.nextLine();

        System.out.println("Enter RAM:");
        String RAM = sc.nextLine();

        System.out.println("Enter storage:");
        String storage = sc.nextLine();

        System.out.println("Enter GPU:");
        String GPU = sc.nextLine();

        System.out.println("Enter power supply:");
        String powerSupply = sc.nextLine();

        // Test the Builder Implementation
        Computer computer = new Computer.Builder()
                .setCPU(CPU)
                .setRAM(RAM)
                .setStorage(storage)
                .setGPU(GPU)
                .setPowerSupply(powerSupply)
                .build();

        System.out.println("Computer configuration: " + computer);
    }
}