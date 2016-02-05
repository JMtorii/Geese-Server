package com.geese.server.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geese.server.Application;
import com.geese.server.domain.Flock;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * Created by JMtorii on 2015-11-25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
public class FlockControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(FlockControllerTest.class);
    private MockMvc mvc;
    private Random random;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        random = new Random();
    }

    @Test
    public void getNearbyFlocksFailsIfLatitudeIsTooLow() throws Exception {
        float invalidLatitude = -200.0f;
        float validLongitude = 90.0f;

        sendGetNearbyFlocksRequest(invalidLatitude, validLongitude)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void getNearbyFlocksFailsIfLatitudeIsTooHigh() throws Exception {
        float invalidLatitude = 200.0f;
        float validLongitude = 90.0f;

        sendGetNearbyFlocksRequest(invalidLatitude, validLongitude)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void getNearbyFlocksFailsIfLongitudeIsTooLow() throws Exception {
        float validLatitude = 90.0f;
        float invalidLongitude = -200.0f;

        sendGetNearbyFlocksRequest(validLatitude, invalidLongitude)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void getNearbyFlocksFailsIfLongitudeIsTooHigh() throws Exception {
        float validLatitude = 90.0f;
        float invalidLongitude = 200.0f;

        sendGetNearbyFlocksRequest(validLatitude, invalidLongitude)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Ignore
    @Test
    public void getNearbyFlocksFindsAllNearbyFlocks() throws Exception {
        float validLatitude = 43.46519f;
        float validLongitude = -80.52237f;

        MvcResult result = sendGetNearbyFlocksRequest(validLatitude, validLongitude)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<Flock> flocks = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Flock>>(){});
        int originalFlockSize = flocks.size();

        Flock flock1 = new Flock.Builder()
                .authorid(1)
                .name("test_" + random.nextInt())
                .description("description")
                .latitude(43.47172f)
                .longitude(-80.54201f)
                .radius(1)
                .score(0)
                .createdTime(LocalDateTime.now())
                .build();


        sendCreateFlockRequest(flock1);

        MvcResult result2 = sendGetNearbyFlocksRequest(validLatitude, validLongitude)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<Flock> flocks2 = objectMapper.readValue(result2.getResponse().getContentAsString(), new TypeReference<List<Flock>>(){});
        Assert.assertThat(flocks2.size(), Matchers.equalTo(originalFlockSize + 1));
    }

    private ResultActions getFlockRequest(int flockId) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(String.format("/flock/%d", flockId)));
    }

    private ResultActions sendCreateFlockRequest(Flock flock) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post("/flock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(flock)));
    }

    private ResultActions sendGetNearbyFlocksRequest(float latitude, float longitude) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get("/flock/getNearbyFlocks")
                    .header("X-AUTH-TOKEN", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGVtYWlsLmNvbSJ9.qufWHyPzMLAwaF_1QARepchXGRTx5fsuHOJXcfnF6OLTBbcD6PyD575geXdU2zwbwIYL_5ThGRSMlb7Qa_rpxw")
                    .param("latitude", Float.toString(latitude))
                    .param("longitude", Float.toString(longitude)));
    }
}
