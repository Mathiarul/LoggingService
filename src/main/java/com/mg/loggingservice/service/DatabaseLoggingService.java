package com.mg.loggingservice.service;

import com.mg.loggingservice.entity.LogEntity;
import com.mg.loggingservice.repository.LogRepository;
import org.springframework.stereotype.Service;

@Service
public class DatabaseLoggingService implements LoggingService {

  private final LogRepository logRepository;

  public DatabaseLoggingService(LogRepository logRepository) {
    this.logRepository = logRepository;
  }

  @Override
  public void write(LogEntity logEntity) {
    logRepository.save(logEntity);
  }
}
