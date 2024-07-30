package SingletonPatternExample;

public class Logger {
    // Step 2: Define a Singleton Class
    private static Logger instance;

    // Ensure the constructor is private
    private Logger() {

    }

    //Provide a public static method to get the instance of the Logger class
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("Log message: " + message);
    }
}
