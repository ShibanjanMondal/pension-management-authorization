package dh.pensionmanagement.webservices.auth.service;

import dh.pensionmanagement.webservices.auth.exception.handler.PensionerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class FetchProcessedPensionDetailsTests {
    @Autowired
    FetchProcessedPensionDetails fetchProcessedPensionDetails;

    @Autowired
    PostAuthProxy postAuthProxy;
    @Test
    public void processPension_happyPath(){
        String aadhaar = "3333-3333-3333";
        Assertions.assertNotNull(fetchProcessedPensionDetails.processPension(aadhaar,postAuthProxy));
    }

    @Test
    public void processPension_negPath(){
        String aadhaar = "3333-3333-33";
        Assertions.assertThrows(PensionerNotFoundException.class,
                ()->fetchProcessedPensionDetails.processPension(aadhaar,postAuthProxy));
    }
}
