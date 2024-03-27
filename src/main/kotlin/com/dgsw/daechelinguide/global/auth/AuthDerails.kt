package com.dgsw.daechelinguide.global.auth

import com.dgsw.daechelinguide.domain.member.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthDerails(
    private val member: Member
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(member.role.name))
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return member.id.toString()
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