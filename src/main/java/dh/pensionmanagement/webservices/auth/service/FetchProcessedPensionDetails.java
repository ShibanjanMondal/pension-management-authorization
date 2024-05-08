package dh.pensionmanagement.webservices.auth.service;

import dh.pensionmanagement.webservices.auth.exception.handler.PensionerDetailsServiceUnavailableException;
import dh.pensionmanagement.webservices.auth.exception.handler.PensionerNotFoundException;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FetchProcessedPensionDetails {

    public ResponseEntity<?> processPension(String aadhaar, PostAuthProxy proxy){
        try {
            return proxy.retrieveProcessedPension(aadhaar);
        }catch (FeignException.NotFound e){
            throw new PensionerNotFoundException("Pensioner does not exists");
        }catch (FeignException.ServiceUnavailable e){
            throw new PensionerDetailsServiceUnavailableException("Service Unavailable");
        }
    }
}
