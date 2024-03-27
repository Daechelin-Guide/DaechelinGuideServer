package com.dgsw.daechelinguide.global.filter

import com.dgsw.daechelinguide.global.filter.security.token.TokenParser
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
class FilterConfig(
    private val tokenParser: TokenParser,
    private val objectMapper: ObjectMapper
): SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {
    override fun init(builder: HttpSecurity?) {}

    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(JwtFilter(tokenParser), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(ExceptionFilter(objectMapper), JwtFilter::class.java)
    }
}