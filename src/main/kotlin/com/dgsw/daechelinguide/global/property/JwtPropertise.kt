package com.dgsw.daechelinguide.global.property

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.*
import javax.crypto.SecretKey

@ConfigurationProperties("jwt")
class JwtProperties(
    secretKey: String,
    val accessExpirationTime: Long,
    val refreshExpirationTime: Long
) {
    val secretKey: SecretKey = Keys.hmacShaKeyFor(
        Base64.getEncoder().encodeToString(secretKey.toByteArray())
            .toByteArray(Charsets.UTF_8)
    )
}