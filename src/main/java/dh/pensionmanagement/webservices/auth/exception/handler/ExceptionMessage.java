package dh.pensionmanagement.webservices.auth.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ExceptionMessage {
    @JsonFormat(shape = JsonFormat.Shape.OBJECT ,pattern = "dd-MMM-yyyy hh:mm:ss")
    private Date timeStamp;
    private String errorMessage;
    private int errorCode;
}
