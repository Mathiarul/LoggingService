package com.mg.loggingservice.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@SuppressWarnings({"unused", "HardcodedFileSeparator"})
class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[]{
        ApplicationConfiguration.class
    };
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return null;
  }

  @Override
  protected String[] getServletMappings() {

    return new String[]{
        "/api/*",
        "/"
    };
  }
}
