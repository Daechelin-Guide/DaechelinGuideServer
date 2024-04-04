package com.dgsw.daechelinguide.global.filter

import com.dgsw.daechelinguide.global.error.DaechelingException
import com.dgsw.daechelinguide.global.error.ErrorProperty
import com.dgsw.daechelinguide.global.error.ErrorResponse
import com.dgsw.daechelinguide.global.error.GlobalErrorCode
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets

class ExceptionFilter(
    private val objectMapper: ObjectMapper
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: DaechelingException) {
            e.printStackTrace()
            errorToJson(e.errorProperty, response)
        } catch (e: Exception) {
            e.printStackTrace()
            when(e.cause) {
                is DaechelingException -> {
                    errorToJson((e.cause as DaechelingException).errorProperty, response)
                } else -> {
                    errorToJson(GlobalErrorCode.INTERNAL_SERVER_ERROR, response)
                }
            }
        }
    }

    private fun errorToJson(errorProperty: ErrorProperty, response: HttpServletResponse) {
        response.status = errorProperty.status()
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(objectMapper.writeValueAsString(ErrorResponse.of(errorProperty)))
    }

}