package ucr.ac.fitfun.exceptions;

public class InvalidInputException extends RuntimeException {

    private final String field;

    public InvalidInputException(String field) {
        super("Invalid Field %s".formatted(field));
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
