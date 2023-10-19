package ucr.ac.fitfun.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionResponseController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionResponseController.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                ex.getCode()
        );
        logger.error(response.message(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleUnknown(Throwable ex) {
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                ErrorCodes.UNKNOWN_ERROR
        );
        logger.error(response.message(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                ErrorCodes.AUTHENTICATION_ERROR
        );
        logger.error(response.message(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
