package com.mg.loggingservice.service;


import com.mg.loggingservice.entity.LogEntity;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class FileLoggingService implements LoggingService {

  private static final Logger LOG = Logger.getLogger(FileLoggingService.class);

  @Value("${log.file.path}")
  private String logPath;

  @Override
  public void write(LogEntity logEntity) {
    try (FileWriter fileWriter = new FileWriter(logPath, true)) {
      fileWriter.write(logEntity.toString());
      fileWriter.write(System.lineSeparator());
    } catch (IOException e) {
      LOG.error("Error writing log to file", e);
    }
  }
}

