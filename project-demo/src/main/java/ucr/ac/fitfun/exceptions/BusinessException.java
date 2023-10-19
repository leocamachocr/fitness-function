package ucr.ac.fitfun.exceptions;

public class BusinessException extends RuntimeException {

    private int code;

    public BusinessException(String message) {

        super(message);
        code = ErrorCodes.ERROR_NOT_IDENTIFIED;
    }

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
