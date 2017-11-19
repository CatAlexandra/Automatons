package palyaeva.exception;

public class MyException extends RuntimeException {

    public String message;

    public MyException() {
    }

    public MyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}