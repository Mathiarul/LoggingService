package com.mg.loggingservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mg.loggingservice.entity.LogEntity;
import com.mg.loggingservice.repository.LogRepository;
import com.mg.loggingservice.utils.Testutils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DatabaseLoggingService.class})
@ExtendWith(SpringExtension.class)
class DatabaseLoggingServiceTest {

  @Autowired
  private DatabaseLoggingService databaseLoggingService;

  @MockBean
  private LogRepository logRepository;

  /**
   * Method under test: {@link DatabaseLoggingService#write(LogEntity)}
   */
  @Test
  void testWrite() {
    LogEntity logEntity = Testutils.buildLog();

    when(logRepository.save(logEntity)).thenReturn(logEntity);

    databaseLoggingService.write(logEntity);

    verify(logRepository).save(logEntity);
    assertEquals("42", logEntity.getApplicationId());
    assertEquals("42", logEntity.getTraceId());
    assertEquals("Timestamp", logEntity.getTimestamp());
    assertEquals("S1", logEntity.getSeverity());
    assertEquals("42", logEntity.getRequestId());
    assertEquals("Not all who wander are lost", logEntity.getMessage());
    assertEquals(1L, logEntity.getId().longValue());
    assertEquals("Component Name", logEntity.getComponentName());
  }
}

