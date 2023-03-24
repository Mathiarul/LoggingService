package com.mg.loggingservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mg.loggingservice.entity.LogEntity;
import com.mg.loggingservice.utils.Testutils;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.ReflectionUtils;

@SpringBootTest
@TestPropertySource(properties = {
    "log.file.path = ${java.io.tmpdir}/test.log"
})
class FileLoggingServiceTest {


  @Test
  void testWrite(@Value("${log.file.path}") String logPath) throws IOException {
    FileLoggingService loggingService = new FileLoggingService();

    // Use ReflectionUtils to set the logPath field
    Field logPathField = ReflectionUtils.findField(FileLoggingService.class, "logPath");
    ReflectionUtils.makeAccessible(logPathField);
    ReflectionUtils.setField(logPathField, loggingService, logPath);

    LogEntity logEntity = Testutils.buildLog();

    loggingService.write(logEntity);
    String expectedLog = logEntity.toString() + System.lineSeparator();
    String actualLog = FileUtils.readFileToString(new File(logPath), "UTF-8");
    assertEquals(expectedLog, actualLog);
  }
}
