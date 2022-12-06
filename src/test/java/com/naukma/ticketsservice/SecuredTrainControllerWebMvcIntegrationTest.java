package com.naukma.ticketsservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecuredTrainControllerWebMvcIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }



    @WithMockUser(username = "user", password = "123", roles = "USER")
    @Test
    public void givenGetRequestOnPrivateController_shouldReturn403() throws Exception {
        mvc.perform(get("/api/v1/train").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }


    @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
    @Test
    public void givenGetRequestOnPrivateControllerWithAdmin_shouldReturn200() throws Exception {
        mvc.perform(get("/api/v1/train").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void givenUnauthorizedUserOnPrivateController() throws Exception {
        mvc.perform(get("/api/v1/train").contentType(MediaType.APPLICATION_JSON))
                .andExpect(redirectedUrlTemplate("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }




}