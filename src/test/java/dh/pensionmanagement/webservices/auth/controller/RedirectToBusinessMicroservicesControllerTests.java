package dh.pensionmanagement.webservices.auth.controller;

import dh.pensionmanagement.webservices.auth.util.jwt.models.AuthenticationRequest;
import dh.pensionmanagement.webservices.auth.util.jwt.models.AuthenticationResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class RedirectToBusinessMicroservicesControllerTests {
Logger logger = LoggerFactory.getLogger(RedirectToBusinessMicroservicesControllerTests.class);
    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    public LoginSignUpHandler loginSignUpHandler;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void processedPensionDetails_HappyPath() throws Exception{
        String aadhaar = "3333-3333-3333";
        AuthenticationRequest authenticationRequest =
                new AuthenticationRequest("admin","pass");
        ResponseEntity<AuthenticationResponse> r = loginSignUpHandler.createAuthenticationToken(authenticationRequest);
        Assertions.assertNotNull(r);
        String jwt = Objects.requireNonNull(r.getBody()).getJwt();
        mvc.perform(post("/ProcessPension/"+aadhaar)
                .header("Authorization","Bearer "+jwt)
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
