package core.fu4sbackend.exception;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;
import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        if (exception instanceof BadCredentialsException) {
            return new ResponseEntity<>("Bad credentials", HttpStatus.UNAUTHORIZED);
        }
        else if (exception instanceof ResourceNotFoundException) {
            return new ResponseEntity<>("No files found", HttpStatus.NO_CONTENT);
        }
        else if (exception instanceof InvalidParameterException) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
        System.out.print("Root cause: ");
        Arrays.stream(exception.getStackTrace()).findFirst().ifPresent(System.out::println);
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
