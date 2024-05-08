package dh.pensionmanagement.webservices.auth.util.jwt;

import dh.pensionmanagement.webservices.auth.controller.LoginSignUpHandler;
import dh.pensionmanagement.webservices.auth.model.NewUser;
import dh.pensionmanagement.webservices.auth.model.UserDetailsEntity;
import dh.pensionmanagement.webservices.auth.model.repo.UserRepository;
import dh.pensionmanagement.webservices.auth.service.MyUserDetailsService;
import dh.pensionmanagement.webservices.auth.util.jwt.models.AuthenticationRequest;
import dh.pensionmanagement.webservices.auth.util.jwt.models.AuthenticationResponse;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

@SpringBootTest
@Slf4j
class JwtUtilTests {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    LoginSignUpHandler loginSignUpHandler;

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private UserRepository repository;


    String token;

    @BeforeEach
    public void generateToken() throws Exception {
        AuthenticationRequest authenticationRequest =
                new AuthenticationRequest("admin", "pass");
        ResponseEntity<AuthenticationResponse> r = loginSignUpHandler.createAuthenticationToken(authenticationRequest);
        token = Objects.requireNonNull(r.getBody()).getJwt();
    }

    @Test
    public void extractUsernameTests() {
        Assertions.assertEquals("admin", jwtUtil.extractUsername(token));
    }

    @Test
    public void extractExpirationTests(){
        Assertions.assertNotNull(jwtUtil.extractExpiration(token));
    }


    @Test
    public void validateTokenTests_validToken(){
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
        Assertions.assertEquals(true,jwtUtil.validateToken(token,userDetails));
    }

    @Test
    public void validateTokenTests_inValidToken() throws Exception {
        NewUser newUser = new NewUser("foo","email","foo");
        repository.save(new UserDetailsEntity(newUser.getUsername(),
                newUser.getEmail(), newUser.getPassword()));
        int initial_val = jwtUtil.timeDiffInMillSec;
        jwtUtil.setTimeDiffInMillSec_OnlyForTest(1000);
        AuthenticationRequest authenticationRequest =
                new AuthenticationRequest("foo", "foo");
        ResponseEntity<AuthenticationResponse> r = loginSignUpHandler.createAuthenticationToken(authenticationRequest);
        token = Objects.requireNonNull(r.getBody()).getJwt();
        Thread.sleep(jwtUtil.timeDiffInMillSec);
        UserDetails userDetails = userDetailsService.loadUserByUsername("foo");
        Assertions.assertThrows(ExpiredJwtException.class,()->jwtUtil.validateToken(token,userDetails));
        jwtUtil.setTimeDiffInMillSec_OnlyForTest(initial_val);
    }
}
