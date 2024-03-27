package com.dgsw.daechelinguide.global.filter.security.token

import com.dgsw.daechelinguide.domain.auth.entity.RefreshToken
import com.dgsw.daechelinguide.domain.auth.presentation.dto.TokenResponse
import com.dgsw.daechelinguide.domain.auth.repository.RefreshTokenRepository
import com.dgsw.daechelinguide.domain.member.entity.value.Role
import com.dgsw.daechelinguide.global.property.JwtProperties
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateJwt(
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun generateTokens(memberId: Long, role: Role) = TokenResponse(
        accessToken = generateAccessToken(memberId, role),
        refreshToken = generateRefreshToken(memberId, role)
    )

    private fun generateAccessToken(memberId: Long, role: Role) =
        Jwts.builder()
            .signWith(jwtProperties.secretKey, SignatureAlgorithm.HS512)
            .setHeaderParam(Header.JWT_TYPE, JwtUtils.ACCESS)
            .setId(memberId.toString())
            .claim(JwtUtils.AUTHORITY, role.name)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExpirationTime * 1000))
            .compact()

    private fun generateRefreshToken(memberId: Long, role: Role): String {
        val token = Jwts.builder()
            .signWith(jwtProperties.secretKey, SignatureAlgorithm.HS512)
            .setHeaderParam(Header.JWT_TYPE, JwtUtils.ACCESS)
            .setId(memberId.toString())
            .claim(JwtUtils.AUTHORITY, role.name)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExpirationTime * 1000))
            .compact()

        val refreshToken = RefreshToken(
            token = token,
            expirationTime = jwtProperties.refreshExpirationTime
        )

        refreshTokenRepository.save(refreshToken)

        return token
    }
}