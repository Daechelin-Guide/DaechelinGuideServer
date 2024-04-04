package com.dgsw.daechelinguide.global.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("neis")
class NeisProperties(
    val key: String,
    val educationCode: String,
    val schoolCode: String
)