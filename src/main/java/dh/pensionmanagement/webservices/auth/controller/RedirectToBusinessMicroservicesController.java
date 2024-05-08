package dh.pensionmanagement.webservices.auth.controller;

import dh.pensionmanagement.webservices.auth.service.FetchProcessedPensionDetails;
import dh.pensionmanagement.webservices.auth.service.PostAuthProxy;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RedirectToBusinessMicroservicesController {
    Logger logger = LoggerFactory.getLogger(RedirectToBusinessMicroservicesController.class);

    @Autowired
    PostAuthProxy postAuthProxy;

    @Autowired
    FetchProcessedPensionDetails fetchProcessedPensionDetails;


    @PostMapping (path="/ProcessPension/{aadhaar}")
    public ResponseEntity<?> processedPensionDetails(@PathVariable String aadhaar){
        return  new ResponseEntity<>(fetchProcessedPensionDetails.processPension(aadhaar,postAuthProxy), HttpStatus.OK);
    }

}
