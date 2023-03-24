package com.mg.loggingservice.service;

import com.mg.loggingservice.entity.LogEntity;

public interface LoggingService {

  void write(LogEntity logEntity);
}
