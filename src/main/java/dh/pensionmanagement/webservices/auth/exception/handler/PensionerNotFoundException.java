package dh.pensionmanagement.webservices.auth.exception.handler;

public class PensionerNotFoundException extends RuntimeException{
    public static final long serialVersionID = 1L;
    public final String errorMessage;

    public PensionerNotFoundException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
