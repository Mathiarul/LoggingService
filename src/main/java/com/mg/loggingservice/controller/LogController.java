package com.mg.loggingservice.controller;

import com.mg.loggingservice.entity.LogEntity;
import com.mg.loggingservice.service.LoggingService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

  private static final Logger LOG = Logger.getLogger(LogController.class);

  private final List<LoggingService> loggingServices;

  public LogController(List<LoggingService> loggingServices) {
    this.loggingServices = loggingServices;
  }

  @PostMapping("/api/v1/logging/saveLog")
  public ResponseEntity<String> saveLog(@RequestBody @Valid LogEntity logEntity, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      String errorMessage = bindingResult.getFieldErrors().stream()
          .map(FieldError::getDefaultMessage)
          .collect(Collectors.joining(", "));
      return ResponseEntity.badRequest().body("Invalid log object: " + errorMessage);
    }

    loggingServices.forEach(service -> service.write(logEntity));
    return ResponseEntity.ok("Log saved successfully");
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<String> handleException(Exception ex) {
    LOG.error("An error occurred while processing the request", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("An error occurred while processing the request: " + ex.getMessage());
  }

}
