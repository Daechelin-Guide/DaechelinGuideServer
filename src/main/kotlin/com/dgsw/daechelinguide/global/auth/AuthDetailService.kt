package com.dgsw.daechelinguide.global.auth

import com.dgsw.daechelinguide.domain.member.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailService(
    private val memberRepository: MemberRepository
): UserDetailsService {
    override fun loadUserByUsername(memberId: String): UserDetails {
        val member = memberRepository.findMemberById(memberId.toLong())
            ?: throw RuntimeException()
        return AuthDerails(member)
    }
}