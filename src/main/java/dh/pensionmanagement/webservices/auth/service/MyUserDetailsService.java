package dh.pensionmanagement.webservices.auth.service;

import dh.pensionmanagement.webservices.auth.model.UserDetailsEntity;
import dh.pensionmanagement.webservices.auth.model.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
    @Autowired
    UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsEntity newUser = userRepository.findByUsername(username);
        if (newUser == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(newUser.getUsername(), newUser.getPassword(),true, true, true, true, new ArrayList<>());
    }

}
