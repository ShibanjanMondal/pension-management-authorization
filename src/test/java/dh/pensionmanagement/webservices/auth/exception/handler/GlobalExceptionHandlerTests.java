package dh.pensionmanagement.webservices.auth.exception.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@SpringBootTest
class GlobalExceptionHandlerTests {

    @Autowired
    GlobalExceptionHandler globalExceptionHandler;
    @Test
    public void incorrectCredentialsTests(){
        IncorrectCredentialsException exception = new IncorrectCredentialsException("exception message");
        ResponseEntity<ExceptionMessage> r = globalExceptionHandler.incorrectCredentials(exception);
        Assertions.assertEquals("exception message", Objects.requireNonNull(r.getBody()).getErrorMessage());
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(),r.getBody().getErrorCode());
        Assertions.assertNotNull(r.getBody().getTimeStamp());
         Assertions.assertNotNull(r.getHeaders());
    }

    @Test
    public void pensionerNotFoundExceptionTests(){
        PensionerNotFoundException exception = new PensionerNotFoundException("exception message");
        ResponseEntity<ExceptionMessage> r = globalExceptionHandler.pensionerNotFoundException(exception);
        Assertions.assertEquals("exception message", Objects.requireNonNull(r.getBody()).getErrorMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(),r.getBody().getErrorCode());
        Assertions.assertNotNull(r.getBody().getTimeStamp());
         Assertions.assertNotNull(r.getHeaders());
    }

    @Test
    public void userAlreadyExistsExceptionTests(){
        UserAlreadyExistsException exception = new UserAlreadyExistsException("exception message");
        ResponseEntity<ExceptionMessage> r = globalExceptionHandler.userAlreadyExistsException(exception);
        Assertions.assertEquals("Username \"exception message\" already exists, try wih different username", Objects.requireNonNull(r.getBody()).getErrorMessage());
        Assertions.assertEquals(HttpStatus.PRECONDITION_FAILED.value(),r.getBody().getErrorCode());
        Assertions.assertNotNull(r.getBody().getTimeStamp());
        Assertions.assertNotNull(r.getHeaders());
    }

    @Test
    public void invalidTokenExceptionTests(){
        InvalidTokenException exception = new InvalidTokenException("exception message");
        ResponseEntity<ExceptionMessage> r = globalExceptionHandler.invalidTokenException(exception);
        Assertions.assertEquals("exception message", Objects.requireNonNull(r.getBody()).getErrorMessage());
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(),r.getBody().getErrorCode());
        Assertions.assertNotNull(r.getBody().getTimeStamp());
        Assertions.assertNotNull(r.getHeaders());
    }

    @Test
    public void pensionerDetailsServiceUnavailableExceptionTests(){
        PensionerDetailsServiceUnavailableException exception = new PensionerDetailsServiceUnavailableException("exception message");
        ResponseEntity<ExceptionMessage> r = globalExceptionHandler.pensionerDetailsServiceUnavailableException(exception);
        Assertions.assertEquals("exception message", Objects.requireNonNull(r.getBody()).getErrorMessage());
        Assertions.assertEquals(HttpStatus.SERVICE_UNAVAILABLE.value(),r.getBody().getErrorCode());
        Assertions.assertNotNull(r.getBody().getTimeStamp());
        Assertions.assertNotNull(r.getHeaders());
    }

}
