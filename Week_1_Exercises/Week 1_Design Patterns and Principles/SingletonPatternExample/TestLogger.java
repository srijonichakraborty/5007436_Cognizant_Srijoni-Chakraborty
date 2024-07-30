package SingletonPatternExample;

public class TestLogger {
    public static void main(String[] args) {
        // Test the Singleton Implementation
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Test to verify both references point to the same instance
        if (logger1 == logger2) {
            System.out.println("Both logger1 and logger2 are the same instance.");
        } else {
            System.out.println("logger1 and logger2 are different instances.");
        }
        
        logger1.log("This is a test log message.");
    }
}