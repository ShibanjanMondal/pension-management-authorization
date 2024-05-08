package dh.pensionmanagement.webservices.auth.exception.handler;

public class PensionerDetailsServiceUnavailableException extends RuntimeException{
    public static final long serialVersionID = 1L;
    public final String errorMessage;


    public PensionerDetailsServiceUnavailableException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
