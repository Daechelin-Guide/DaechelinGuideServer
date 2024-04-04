package com.dgsw.daechelinguide.domain.menu.presentation.dto

data class MealResponse(
    val date: String,
    val breakfast: String?,
    val lunch: String?,
    val dinner: String?
)