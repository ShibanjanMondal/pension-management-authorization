package dh.pensionmanagement.webservices.auth.util.jwt.filter;

import dh.pensionmanagement.webservices.auth.controller.LoginSignUpHandler;
import dh.pensionmanagement.webservices.auth.service.MyUserDetailsService;
import dh.pensionmanagement.webservices.auth.util.jwt.JwtUtil;
import dh.pensionmanagement.webservices.auth.util.jwt.models.AuthenticationRequest;
import dh.pensionmanagement.webservices.auth.util.jwt.models.AuthenticationResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Objects;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
class JwtRequestFilterTests {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MockMvc mvc;
    @Autowired
    JwtRequestFilter filter;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    public LoginSignUpHandler loginSignUpHandler;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void doFilterInternalTests() throws Exception {
        AuthenticationRequest authenticationRequest =
                new AuthenticationRequest("admin","pass");
        ResponseEntity<AuthenticationResponse> r = loginSignUpHandler.createAuthenticationToken(authenticationRequest);
        Assertions.assertNotNull(r);
        String jwt = Objects.requireNonNull(r.getBody()).getJwt();

        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();
        req.addHeader("Authorization","Bearer "+jwt);
        Assertions.assertDoesNotThrow(()->filter.doFilterInternal(req,res,chain));
    }
}
