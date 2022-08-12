package exception;

public class BadJsonException extends Exception {
    public BadJsonException(String message) {
        super(message);
    }
}
