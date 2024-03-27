package com.dgsw.daechelinguide.domain.auth.presentation

import com.dgsw.daechelinguide.domain.auth.presentation.dto.TokenResponse
import com.dgsw.daechelinguide.domain.auth.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(@RequestParam code: String): TokenResponse {
        return authService.login(code)
    }

}