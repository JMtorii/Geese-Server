package com.geese.server.controller;

import com.geese.server.domain.Flock;
import com.geese.server.service.FlockService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RestController
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class})
//@WebAppConfiguration
public class FlockControllerUnitTest {
 
    private MockMvc mockMvc;
 
    @Autowired
    private FlockService flockServiceMock;
 
    //Add WebApplicationContext field here
 
    //The setUp() method is omitted.
 
    @Test
    @Ignore
    public void add_NewFlockEntry_ShouldAddFlockEntryAndRenderViewFlockEntryView() throws Exception {
        Flock created = new Flock.Builder()
                .authorid(1337)
                .name("Test Flock")
                .description("Test description")
                .createdTime(LocalDateTime.now())
                .expireTime(LocalDateTime.now())
                .latitude(0)
                .longitude(0)
                .radius(0)
                .score(0)
                .build();

        when(flockServiceMock.create(isA(Flock.class))).thenReturn(1);
 
        mockMvc.perform(post("/flock/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "description")
                .param("title", "title")
                .sessionAttr("flock", new Flock())
        )
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:flock/{id}"))
                .andExpect(redirectedUrl("/flock/1"))
                .andExpect(model().attribute("id", is("1")))
                .andExpect(flash().attribute("feedbackMessage", is("Flock entry: title was added.")));
 
        ArgumentCaptor<Flock> formObjectArgument = ArgumentCaptor.forClass(Flock.class);
        verify(flockServiceMock, times(1)).create(formObjectArgument.capture());
        verifyNoMoreInteractions(flockServiceMock);
 
        Flock formObject = formObjectArgument.getValue();
 
        assertThat(formObject.getDescription(), is("description"));
        assertNull(formObject.getId());
    }
}