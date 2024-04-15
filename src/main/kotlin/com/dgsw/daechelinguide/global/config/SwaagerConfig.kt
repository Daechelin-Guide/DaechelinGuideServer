package com.dgsw.daechelinguide.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaagerConfig {
    @Bean
    fun openAPI(): OpenAPI {
        val info: Info = Info()
            .title("대슐랭 가이드")
            .version("v1.0.0")
            .description("대슐랭 가이드 API 문서")
        return OpenAPI()
            .components(Components())
            .info(info)
    }
}