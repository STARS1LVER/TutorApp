package star.tutorApp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // para manejar excepciones globales
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class) // para manejar excepciones de tipo RuntimeException
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "ERROR_GENERAL");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class) // para manejar excepciones de tipo BadCredentialsException
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        ErrorResponse error = new ErrorResponse("El email o la contraseña son incorrectos", "INVALID_CREDENTIALS");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class) // para manejar excepciones de tipo UsernameNotFoundException
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "USER_NOT_FOUND");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class) // para manejar excepciones de tipo EmailAlreadyExistsException
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            "EMAIL_ALREADY_EXISTS"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
} 