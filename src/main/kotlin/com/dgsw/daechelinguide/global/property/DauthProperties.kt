package com.dgsw.daechelinguide.global.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("dauth")
class DauthProperties(
    val clientId: String,
    val clientSecret: String
)