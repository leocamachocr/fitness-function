package ucr.ac.fitfun.exceptions;

public class ErrorCodes {

    // Basic Business Validations 1000 - 1999

    public static final int ERROR_NOT_IDENTIFIED = 1_001;
    public static final int REQUIRED_FIELDS = 1_002;
    public static final int UNKNOWN_ERROR = 1_003;

    // Security Validations
    public static final int UNAUTHORIZED = 401_01;
    public static final int AUTHENTICATION_ERROR = 401_02;
    public static final int SESSION_EXPIRED = 401_03;


}
