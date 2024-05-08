package dh.pensionmanagement.webservices.auth.controller;

import dh.pensionmanagement.webservices.auth.exception.handler.IncorrectCredentialsException;
import dh.pensionmanagement.webservices.auth.exception.handler.UserAlreadyExistsException;
import dh.pensionmanagement.webservices.auth.model.Messenger;
import dh.pensionmanagement.webservices.auth.model.NewUser;
import dh.pensionmanagement.webservices.auth.model.UserDetailsEntity;
import dh.pensionmanagement.webservices.auth.model.repo.UserRepository;
import dh.pensionmanagement.webservices.auth.service.PostAuthProxy;
import dh.pensionmanagement.webservices.auth.util.jwt.JwtUtil;
import dh.pensionmanagement.webservices.auth.util.jwt.models.AuthenticationRequest;
import dh.pensionmanagement.webservices.auth.util.jwt.models.AuthenticationResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Slf4j
public class LoginSignUpHandler {
Logger logger= LoggerFactory.getLogger(LoginSignUpHandler.class);
    @Autowired
    private UserRepository repository;

    @Autowired
    JwtUtil jwtTokenUtil;

    @Autowired
    PostAuthProxy postAuthProxy;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping(path = "/sign-up")
    public ResponseEntity<Messenger> createUser(@RequestBody NewUser newUser){
        logger.info(newUser.getUsername());
        logger.info(newUser.getEmail());
        logger.info(newUser.getPassword());
            if(repository.findByUsername(newUser.getUsername())!= null)
                throw new UserAlreadyExistsException(newUser.getUsername());
            repository.save(new UserDetailsEntity(newUser.getUsername(),
                    newUser.getEmail(), newUser.getPassword()));
            return new ResponseEntity<>(new Messenger("New User created successfully"), HttpStatus.OK);

    }


    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new IncorrectCredentialsException("Incorrect username or password");
        }
        final org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService.loadUserByUsername(
                authenticationRequest.getUsername()
        );
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return new ResponseEntity<>(new AuthenticationResponse(jwt),HttpStatus.OK);
    }

}








