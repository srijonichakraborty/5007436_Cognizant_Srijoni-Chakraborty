package ObserverPatternExample;

import java.util.Scanner;

public class TestObserverPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StockMarket stockMarket = new StockMarket();

        Observer mobileApp = new MobileApp("MobileApp");
        Observer webApp = new WebApp("WebApp");

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        System.out.println("Enter stock name:");
        String stockName = sc.nextLine();

        System.out.println("Enter stock price:");
        double stockPrice = sc.nextDouble();

        stockMarket.setStockInfo(stockName, stockPrice);
    }
}