package dh.pensionmanagement.webservices.auth.exception.handler;

public class UserAlreadyExistsException extends RuntimeException{
    public static final long serialVersionID = 1L;
    public final String errorMessage;
    public UserAlreadyExistsException(String username){
        errorMessage ="Username \""+username+"\" already exists, try wih different username";
    }
}
