package com.geese.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geese.server.Application;
import com.geese.server.dao.GooseDAO;
import com.geese.server.dao.impl.GooseDAOImpl;
import com.geese.server.domain.Goose;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by JMtorii on 2015-11-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
public class GooseControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Ignore
    @Test
    public void createValidGoose() throws Exception {
        int gooseId1 = 1000;
        Goose goose1 = new Goose.Builder(
                gooseId1,
                "name-1000",
                "email-1000",
                true
        )
                .password("password-1000")
                .salt("salt-1000")
                .build();

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/goose")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(goose1)))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()))
                .andReturn();
    }

    @Ignore
    @Test
    public void getGooseWhenGooseExists() throws Exception {
        setUpGeese();

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/goose/1000"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andReturn();

        Goose goose = objectMapper.readValue(result.getResponse().getContentAsString(), Goose.class);
        Assert.assertEquals(1000, goose.getId());
    }

    // this is kind of stupid, but it should work
    private void setUpGeese() {
        int gooseId1 = 1000;
        Goose goose1 = new Goose.Builder(
                gooseId1,
                "name-1000",
                "email-1000",
                true
        )
                .password("password-1000")
                .salt("salt-1000")
                .build();

        int gooseId2 = 2000;
        Goose goose2 = new Goose.Builder(
                gooseId2,
                "name-2000",
                "email-2000",
                true
        )
                .password("password-2000")
                .salt("salt-2000")
                .build();

        GooseDAO gooseDAO = new GooseDAOImpl();
        gooseDAO.create(goose1);
        gooseDAO.create(goose2);
    }
}
