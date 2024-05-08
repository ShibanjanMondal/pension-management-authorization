package dh.pensionmanagement.webservices.auth.exception.handler;

public class InvalidTokenException extends RuntimeException{
    public final String errorMessage;

    public InvalidTokenException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
