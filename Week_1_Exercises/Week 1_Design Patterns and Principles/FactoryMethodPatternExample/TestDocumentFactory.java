package FactoryMethodPatternExample;

import java.util.Scanner;

public class TestDocumentFactory {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter document type (Word, PDF, Excel):");
        String docType = sc.nextLine();

        DocumentFactory factory = null;

        switch (docType) {
            case "Word":
                factory = new WordDocumentFactory();
                break;
            case "PDF":
                factory = new PdfDocumentFactory();
                break;
            case "Excel":
                factory = new ExcelDocumentFactory();
                break;
            default:
                System.out.println("Unknown document type.");
                return;
        }

        Document document = factory.createDocument();
        document.open();
    }
}
