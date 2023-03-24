package com.mg.loggingservice.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {

  @Value("${logging.destinations}")
  private List<String> logDestinations;

  @Autowired
  private FileLoggingService fileLoggingService;

  @Autowired
  private DatabaseLoggingService databaseLoggingService;

  @Bean
  public List<LoggingService> logDestinationList() {
    List<LoggingService> destinations = new ArrayList<>();
    for (String destination : logDestinations) {
      if (destination.equalsIgnoreCase("file")) {
        destinations.add(fileLoggingService);
      } else if (destination.equalsIgnoreCase("database")) {
        destinations.add(databaseLoggingService);
      }
    }
    return destinations;
  }
}

