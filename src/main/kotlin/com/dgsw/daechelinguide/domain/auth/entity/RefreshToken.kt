package com.dgsw.daechelinguide.domain.auth.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive

@RedisHash(value = "refresh_token")
class RefreshToken(
    @Id
    val token: String,
    @TimeToLive
    val expirationTime: Long
)