package com.dgsw.daechelinguide.global.security.principle

import com.dgsw.daechelinguide.domain.member.entity.value.Role

interface CustomDetails {
    val memberId: Long
    val role: Role
}