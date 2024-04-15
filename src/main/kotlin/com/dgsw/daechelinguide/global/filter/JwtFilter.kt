package com.dgsw.daechelinguide.global.filter

import com.dgsw.daechelinguide.global.security.token.JwtUtils
import com.dgsw.daechelinguide.global.security.token.TokenParser
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtFilter(
    private val tokenParser: TokenParser
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolvedToken(request)

        SecurityContextHolder.clearContext()
        token?.let {
            SecurityContextHolder.getContext().authentication = tokenParser.getAuthentication(token)
        }

        filterChain.doFilter(request, response)
    }

    private fun resolvedToken(request: HttpServletRequest): String? =
        request.getHeader(JwtUtils.HEADER)?.also {
            if(it.startsWith(JwtUtils.PREFIX)) {
                return it.substring(JwtUtils.PREFIX.length)
            }
        }
}