package core.fu4sbackend.exception;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        exception.printStackTrace();
        return switch (exception) {
            case BadCredentialsException badCredentialsException ->
                    new ResponseEntity<>("Bad credentials", HttpStatus.UNAUTHORIZED);
            case MalformedJwtException malformedJwtException ->
                    new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
            case NoSuchElementException noSuchElementException ->
                    new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }
}
