package hello.jdbc.repository.ex;

public class MyDuplicateKeyException extends MyDBException {
    public MyDuplicateKeyException(Throwable cause) {
        super(cause);
    }

    public MyDuplicateKeyException(String message) {
        super(message);
    }

    public MyDuplicateKeyException() {
    }

    public MyDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
