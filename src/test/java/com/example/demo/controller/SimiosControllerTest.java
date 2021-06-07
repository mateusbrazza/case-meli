package com.example.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.demo.service.SimiosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SimiosController.class})
@ExtendWith(SpringExtension.class)
public class SimiosControllerTest {

    @Autowired
    private SimiosController simiosController;

    @MockBean
    private SimiosService simiosService;


    @Test
    public void testStatusdna() throws Exception {
        when(this.simiosService.getStatusDNA()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stats");
        MockMvcBuilders.standaloneSetup(this.simiosController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testVerifySimianOK() throws Exception {
        when(this.simiosService.isSimian((String[]) any())).thenReturn(true);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/simian")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String[]{new String()}));
        MockMvcBuilders.standaloneSetup(this.simiosController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testVerifySimianForbidden() throws Exception {
        when(this.simiosService.isSimian((String[]) any())).thenReturn(false);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/simian")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String[]{new String()}));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.simiosController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isForbidden());
    }




}

