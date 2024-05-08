package dh.pensionmanagement.webservices.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dh.pensionmanagement.webservices.auth.exception.handler.UserAlreadyExistsException;
import dh.pensionmanagement.webservices.auth.model.ExistingUser;
import dh.pensionmanagement.webservices.auth.model.NewUser;
import dh.pensionmanagement.webservices.auth.model.UserDetailsEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class LoginSignUpHandlerTests {

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
    public void createUserTests() throws Exception {

        //validate new user signup
        NewUser newUser = new NewUser("foo", "foo@email.com", "pass");
        String body = (new ObjectMapper()).valueToTree(newUser).toString();
        mvc.perform(post("/sign-up").content(body).contentType("application/json")).andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("New User created successfully"));

    }
    @Test
    public void createUserTests_negPath() throws Exception {
        NewUser newUser = new NewUser("admin", "foo@email.com", "pass");
        String body = (new ObjectMapper()).valueToTree(newUser).toString();
        mvc.perform(post("/sign-up")
                        .content(body)
                        .contentType("application/json"))
                .andExpect(status().isPreconditionFailed())
                        .andExpect(jsonPath("$.errorMessage").isNotEmpty());

        Assertions.assertThrows(UserAlreadyExistsException.class,
                ()->loginSignUpHandler.createUser(newUser));
    }

    @Test
    public void createAuthenticationTokenTests_happyPath() throws Exception {
        //validate if both username and password is correct
        ExistingUser user = new ExistingUser("admin", "pass");
        String body = (new ObjectMapper()).valueToTree(user).toString();
        mvc.perform(post("/authenticate").content(body).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").isNotEmpty());

    }

    @Test
    public void createAuthenticationTokenTests_negPath() throws Exception {
        //validate if username is wrong
        ExistingUser user = new ExistingUser("admi", "pass");
        String body = (new ObjectMapper()).valueToTree(user).toString();
        mvc.perform(post("/authenticate").content(body).contentType("application/json"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.errorMessage").value("Incorrect username or password"));
        //validate if password is wrong
        user.setUsername("admin");
        user.setPassword("pas");
        body = (new ObjectMapper()).valueToTree(user).toString();
        mvc.perform(post("/authenticate").content(body).contentType("application/json"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.errorMessage").value("Incorrect username or password"));
    }


}
