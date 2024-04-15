package com.dgsw.daechelinguide.global.security.service

import com.dgsw.daechelinguide.global.security.principle.CustomDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SecurityService {

    fun getCurrentUserId(): Long {
        return (SecurityContextHolder.getContext().authentication.principal as CustomDetails).memberId
    }
}