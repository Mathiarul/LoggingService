package com.mg.loggingservice.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenApi30Config {

  private static final String API_TITLE = "Log Api";

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info().title(API_TITLE));
  }

  @Bean
  public OpenApiCustomiser serverOpenApiCustomiser() {
    return openAPI -> {
      Server httpsServer = new Server();
      httpsServer.setUrl(openAPI.getServers().get(0).getUrl().replace("http", "https"));
      httpsServer.setDescription(openAPI.getServers().get(0).getDescription());
      openAPI.addServersItem(httpsServer);
    };
  }
}
