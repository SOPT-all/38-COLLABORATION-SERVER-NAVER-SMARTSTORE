package org.sopt.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("NAVER SMARTSTORE API")
                .version("1.0")
                .description("SOPT 38th 합동세미나 웹-데스크탑 1조 NAVER SMARTSTORE 서버 API 문서입니다.");

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(info);
    }
}