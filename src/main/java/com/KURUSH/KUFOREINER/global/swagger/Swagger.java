package com.KURUSH.KUFOREINER.global.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class Swagger {
    //http://localhost:8080/swagger-ui/index.html#/
    @Bean
    public OpenAPI openAPI() {

        // jwt 토큰 설정
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)  // HTTP 기반의 보안 스키마를 사용
                .scheme("bearer")  // 인증 스키마로 Bearer Token을 사용
                .bearerFormat("JWT") // Bearer Token의 형식은 JWT
                .in(SecurityScheme.In.HEADER) //토큰이 HTTP 헤더에 위치함
                .name("Authorization"); // HTTP 헤더의 이름은 "Authorization"
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("jwt_token");

        return new OpenAPI()
                .info(apiInfo())
                .components(new Components().addSecuritySchemes("jwt_token", securityScheme))
                .security(Collections.singletonList(securityRequirement));
    }

    // Swagger 기본 정보 설정
    private Info apiInfo() {
        return new Info()
                .title("KUIT 헤커톤 꼬물가이드 ")
                .description("건대 유학생을 위한 꼬물가이드 API 명세서 입니다.")
                .version("1.0.0");
    }
}
