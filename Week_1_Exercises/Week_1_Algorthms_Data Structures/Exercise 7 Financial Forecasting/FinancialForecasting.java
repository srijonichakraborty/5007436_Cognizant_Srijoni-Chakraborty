import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FinancialForecasting {
    
    private static Map<Integer, Double> memo = new HashMap<>();

    public static double predictFutureValue(int yr, double initialValue, double growthRate) {
        
        if (yr == 0) {
            return initialValue;
        }
        
        if (memo.containsKey(yr)) {
            return memo.get(yr);
        }
        
        double futureValue = predictFutureValue(yr - 1, initialValue, growthRate) * (1 + growthRate);
        
        memo.put(yr, futureValue);
        return futureValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter initial value: ");
        double initialValue = sc.nextDouble();

        System.out.print("Enter annual growth rate (as a decimal): ");
        double growthRate = sc.nextDouble();

        System.out.print("Enter no. of years to predict: ");
        int yrs = sc.nextInt();

        double futureVal = predictFutureValue(yrs, initialValue, growthRate);

        System.out.printf("Predicted value after %d years is: %.2f\n", yrs, futureVal);

        sc.close();
    }
}
