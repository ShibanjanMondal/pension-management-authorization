package dh.pensionmanagement.webservices.auth.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class AllModelsTests {
    @Test
    public void existingUserTests(){
        ExistingUser user=new ExistingUser();
        user.setUsername("a");
        user.setPassword("p");
        Assertions.assertEquals("a",user.getUsername());
        Assertions.assertEquals("p",user.getPassword());
    }
    @Test
    public void messengerTests(){
        Messenger m =new Messenger();
        m.setMessage("messages");
        Assertions.assertEquals("messages",m.getMessage());
    }
    @Test
    public void userDetailsTests(){
        UserDetailsEntity userDetails = new UserDetailsEntity();
        userDetails.setEmail("e@email.com");
        Assertions.assertEquals("e@email.com",userDetails.getEmail());
    }
}
