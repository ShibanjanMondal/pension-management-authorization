package dh.pensionmanagement.webservices.auth.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<ExceptionMessage> incorrectCredentials(IncorrectCredentialsException incorrectCredentialsException){
        ExceptionMessage message= ExceptionMessage.builder()
                .timeStamp(new Date())
                .errorMessage(incorrectCredentialsException.errorMessage)
                .errorCode(HttpStatus.UNAUTHORIZED.value()).build();
        return new ResponseEntity<>(message,getHeaders(), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(PensionerNotFoundException.class)
    public ResponseEntity<ExceptionMessage> pensionerNotFoundException(PensionerNotFoundException exception){
        ExceptionMessage message= ExceptionMessage.builder()
                .timeStamp(new Date())
                .errorMessage(exception.errorMessage)
                .errorCode(HttpStatus.NOT_FOUND.value()).build();
        return new ResponseEntity<>(message,getHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionMessage> userAlreadyExistsException(UserAlreadyExistsException exception){
        ExceptionMessage message= ExceptionMessage.builder()
                .timeStamp(new Date())
                .errorMessage(exception.errorMessage)
                .errorCode(HttpStatus.PRECONDITION_FAILED.value()).build();
        return new ResponseEntity<>(message,getHeaders(), HttpStatus.PRECONDITION_FAILED);
    }
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ExceptionMessage> invalidTokenException(InvalidTokenException exception){
        ExceptionMessage message= ExceptionMessage.builder()
                .timeStamp(new Date())
                .errorMessage(exception.errorMessage)
                .errorCode(HttpStatus.UNAUTHORIZED.value()).build();
        return new ResponseEntity<>(message,getHeaders(), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(PensionerDetailsServiceUnavailableException.class)
    public ResponseEntity<ExceptionMessage> pensionerDetailsServiceUnavailableException(
            PensionerDetailsServiceUnavailableException exception){
        ExceptionMessage message= ExceptionMessage.builder()
                .timeStamp(new Date())
                .errorMessage(exception.errorMessage)
                .errorCode(HttpStatus.SERVICE_UNAVAILABLE.value()).build();
        return new ResponseEntity<>(message,getHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
    }


    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
