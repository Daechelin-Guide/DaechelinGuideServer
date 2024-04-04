package com.dgsw.daechelinguide.global.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@EnableFeignClients(
    basePackages = ["com.dgsw.daechelinguide"],
)
@Configuration
class FeignConfig