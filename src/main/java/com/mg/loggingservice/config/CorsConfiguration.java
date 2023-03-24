package com.mg.loggingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class CorsConfiguration {

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurerImpl();
  }

  private static class WebMvcConfigurerImpl implements WebMvcConfigurer {

    @SuppressWarnings("HardcodedFileSeparator")
    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD");
    }
  }
}
