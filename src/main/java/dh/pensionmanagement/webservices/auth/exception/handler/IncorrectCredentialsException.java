package dh.pensionmanagement.webservices.auth.exception.handler;

public class IncorrectCredentialsException extends RuntimeException{
    public static final long serialVersionID = 1L;
    public final String errorMessage;


    public IncorrectCredentialsException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
