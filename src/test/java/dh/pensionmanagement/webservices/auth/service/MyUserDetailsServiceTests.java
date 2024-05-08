package dh.pensionmanagement.webservices.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
@Slf4j
class MyUserDetailsServiceTests {
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Test
    public void validateLoadByUsername(){
        Assertions.assertNotNull(myUserDetailsService.loadUserByUsername("admin"));
        Assertions.assertThrows(UsernameNotFoundException.class,
                ()-> myUserDetailsService.loadUserByUsername("abcde"));
    }
}
