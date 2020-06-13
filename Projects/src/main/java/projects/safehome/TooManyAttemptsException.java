package projects.safehome;

public class TooManyAttemptsException extends Exception {
    public TooManyAttemptsException(String message) {
        super(message);
    }
}
