package com.mg.loggingservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mg.loggingservice.entity.LogEntity;
import com.mg.loggingservice.service.LoggingService;
import com.mg.loggingservice.utils.Testutils;
import java.util.List;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

@Data
@ContextConfiguration(classes = {LogController.class})
@ExtendWith(SpringExtension.class)
class LogEntityControllerTest {

  @Autowired
  private List<LoggingService> list;

  @Autowired
  private LogController logController;

  @MockBean
  private LoggingService loggingService;

  /**
   * Method under test: {@link LogController#handleException(Exception)}
   */
  @Test
  void testHandleException() {
    ResponseEntity<String> actualHandleExceptionResult = logController.handleException(
        new Exception());
    assertEquals("An error occurred while processing the request: null",
        actualHandleExceptionResult.getBody());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualHandleExceptionResult.getStatusCode());
    assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
  }


  /**
   * Method under test: {@link LogController#saveLog(LogEntity, BindingResult)}
   */
  @Test
  void testSaveLog() throws Exception {
    doNothing().when(loggingService).write(any());

    LogEntity logEntity = Testutils.buildLog();

    String content = (new ObjectMapper()).writeValueAsString(logEntity);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(
            "/api/v1/logging/saveLog")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(logController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
        .andExpect(MockMvcResultMatchers.content().string("Log saved successfully"));
  }

  /**
   * Method under test: {@link LogController#saveLog(LogEntity, BindingResult)}
   */
  @Test
  void testSaveLog2() throws Exception {
    doNothing().when(loggingService).write(any());
    MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
        "/api/v1/logging/saveLog");
    postResult.accept("https://example.org/example");

    LogEntity logEntity = Testutils.buildLog();

    String content = (new ObjectMapper()).writeValueAsString(logEntity);
    MockHttpServletRequestBuilder requestBuilder = postResult.contentType(
        MediaType.APPLICATION_JSON).content(content);
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(logController).build()
        .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500));
  }

}


