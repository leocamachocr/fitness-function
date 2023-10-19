package ucr.ac.fitfun.exceptions;

public record ErrorResponse(
        String message,
        int code
) {
}