package dh.pensionmanagement.webservices.auth.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "process-pension")
public interface PostAuthProxy {

    @PostMapping(path="${server.servlet.context-path}/ProcessPension/{aadhaar}")
    public ResponseEntity<?> retrieveProcessedPension(@PathVariable String aadhaar);
}
