package com.dgsw.daechelinguide.global.config

import com.b1nd.dauth.DAuth
import com.b1nd.dauth.client.DAuthBuilder
import com.dgsw.daechelinguide.global.property.DauthProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DauthConfig (
    private val dauthProperties: DauthProperties
) {
    @Bean
    protected fun dauth(): DAuth = DAuthBuilder.create()
        .clientId(dauthProperties.clientId)
        .clientSecret(dauthProperties.clientSecret)
        .build()
}