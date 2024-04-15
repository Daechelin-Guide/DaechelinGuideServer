package com.dgsw.daechelinguide.domain.rating.presentation.dto.request

data class CreateRatingRequest(
    val score: Double,
    val comment: String?
)