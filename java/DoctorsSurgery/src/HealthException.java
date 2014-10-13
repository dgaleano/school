import java.io.*;      // required for Serialising objects

public class HealthException extends Exception implements Serializable {

    public HealthException() {
        super();
    }

    public HealthException(String message) {
        super(message);
    }
}