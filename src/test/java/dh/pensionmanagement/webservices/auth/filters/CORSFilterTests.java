package dh.pensionmanagement.webservices.auth.filters;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import java.io.IOException;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
 class CORSFilterTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    CORSFilter corsFilter;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCORSFilter() throws ServletException, IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        req.addHeader("Origin","abc");

        corsFilter.doFilter(req,res,chain);

        Assertions.assertNotNull(res.getHeader("Access-Control-Allow-Origin"));
        Assertions.assertNotNull(res.getHeaders("Access-Control-Allow-Credentials"));
        Assertions.assertNotNull(res.getHeaders("Access-Control-Allow-Methods"));
        Assertions.assertNotNull(res.getHeaders("Access-Control-Max-Age"));
        Assertions.assertNotNull(res.getHeaders("Access-Control-Allow-Headers"));
    }

}
