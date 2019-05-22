package by.training.interpol.hash;

public class EncodePasswordException extends  Exception{
    public EncodePasswordException() {
    }

    public EncodePasswordException(String message) {
        super(message);
    }

    public EncodePasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncodePasswordException(Throwable cause) {
        super(cause);
    }
}
