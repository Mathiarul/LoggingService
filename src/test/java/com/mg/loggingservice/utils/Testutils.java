package com.mg.loggingservice.utils;

import com.mg.loggingservice.entity.LogEntity;

public enum Testutils {
  ;

  public static LogEntity buildLog() {
    return LogEntity.builder()
        .applicationId("42")
        .componentName("Component Name")
        .id(1L)
        .message("Not all who wander are lost")
        .requestId("42").severity("S1")
        .timestamp("Timestamp")
        .traceId("42")
        .build();
  }


}
