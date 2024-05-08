package dh.pensionmanagement.webservices.auth.exception.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class ExceptionMessageTests {

    @Test
    public void exceptionMessageTests(){
        Date date = new Date();
        ExceptionMessage message = new ExceptionMessage(date,"err",400);
        Assertions.assertNotNull(message);
        message.setErrorMessage("err");

        message.setTimeStamp(date);
        message.setErrorCode(400);
        Assertions.assertNotNull(message);
        Assertions.assertEquals("err", message.getErrorMessage());
        Assertions.assertEquals(date, message.getTimeStamp());
        Assertions.assertEquals(400, message.getErrorCode());


    }
}
