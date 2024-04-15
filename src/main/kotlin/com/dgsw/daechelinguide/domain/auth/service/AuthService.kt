package com.dgsw.daechelinguide.domain.auth.service

import com.b1nd.dauth.DAuth
import com.dgsw.daechelinguide.domain.auth.presentation.dto.TokenResponse
import com.dgsw.daechelinguide.domain.member.service.MemberService
import com.dgsw.daechelinguide.global.security.token.GenerateJwt
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val dAuth: DAuth,
    private val memberService: MemberService,
    private val generateJwt: GenerateJwt
) {
    fun login(code: String): TokenResponse {
        val tokenInfo = dAuth.issueToken(code)
        val userInfo = dAuth.getUser(tokenInfo.accessToken).user

        val member = if (memberService.existMember(userInfo)) {
            memberService.getUser(userInfo)
        } else {
            memberService.signUp(userInfo)
        }

        return generateJwt.generateTokens(member.id!!, member.role)
    }
}