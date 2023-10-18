package bback.test.proviider.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException() {
        this("유효성이 올바르지 않습니다.");
    }

    public ValidationException(String message) {
        super(message);
    }
}
