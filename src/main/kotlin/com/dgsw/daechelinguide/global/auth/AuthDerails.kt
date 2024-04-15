package com.dgsw.daechelinguide.global.auth

import com.dgsw.daechelinguide.domain.member.entity.value.Role
import com.dgsw.daechelinguide.global.security.principle.CustomDetails
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthDerails(
    override val memberId: Long,
    override val role: Role
): UserDetails, CustomDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return memberId.toString()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}