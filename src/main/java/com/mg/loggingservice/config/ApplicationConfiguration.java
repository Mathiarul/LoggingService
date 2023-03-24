package com.mg.loggingservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan
class ApplicationConfiguration implements WebMvcConfigurer {

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configuration) {
    configuration.defaultContentType(MediaType.APPLICATION_JSON);
  }
}
