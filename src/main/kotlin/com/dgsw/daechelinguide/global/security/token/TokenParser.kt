package com.dgsw.daechelinguide.global.security.token

import com.dgsw.daechelinguide.global.auth.AuthDetailService
import com.dgsw.daechelinguide.global.property.JwtProperties
import io.jsonwebtoken.*
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class TokenParser(
    private val jwtProperties: JwtProperties,
    private val authDetailService: AuthDetailService
) {

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)

        if (claims.header[Header.JWT_TYPE] != JwtUtils.ACCESS) {
            throw RuntimeException()
        }

        val userDetails = authDetailService.loadUserByUsername(claims.body.id)

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getClaims(token: String): Jws<Claims> {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(jwtProperties.secretKey)
                .build()
                .parseClaimsJws(token)
        } catch (e: Exception) {
            when(e) {
                is InvalidClaimException -> throw RuntimeException("잘못된 토큰")
                is ExpiredJwtException -> throw RuntimeException("만료된 토큰")
                is JwtException -> throw RuntimeException("예기치 않은 토큰")
                else -> throw RuntimeException("서버 오류")
            }
        }
    }
}