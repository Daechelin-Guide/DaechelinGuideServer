package com.dgsw.daechelinguide.domain.rating.presentation

import com.dgsw.daechelinguide.domain.rating.presentation.dto.request.CreateRatingRequest
import com.dgsw.daechelinguide.domain.rating.service.RatingService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rating")
class RatingController(
    private val ratingService: RatingService
) {

    @PostMapping("/{menu-id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun createRating(
        @RequestBody @Valid request: CreateRatingRequest,
        @PathVariable(name = "menu-id") menuId: Long
        ) {
        ratingService.createRating(request, menuId)
    }
}