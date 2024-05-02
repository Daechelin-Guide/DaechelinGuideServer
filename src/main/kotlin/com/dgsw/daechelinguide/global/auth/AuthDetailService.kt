package com.dgsw.daechelinguide.global.auth

import com.dgsw.daechelinguide.domain.member.repository.MemberQueryRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailService(
    private val memberQueryRepository: MemberQueryRepository
): UserDetailsService {
    override fun loadUserByUsername(memberId: String): UserDetails {
        val member = memberQueryRepository.findMemberById(memberId.toLong())
            ?: throw RuntimeException()
        return AuthDerails(
            memberId = member.id!!,
            role = member.role
        )
    }
}