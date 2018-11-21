package exceptions;

public class BadCardNumberException extends RuntimeException {

    public BadCardNumberException(String message) {
        super(message);
    }

}
