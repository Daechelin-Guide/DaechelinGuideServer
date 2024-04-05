package com.dgsw.daechelinguide.domain.menu.presentation.dto

data class MealResponse(
    val date: String,
    val breakfast: String?,
    val lunch: String?,
    val dinner: String?
)

data class MealDetailResponse(
    val id: Long,

    val menu: String? = null,

    val date: String,

    val cal: String? = null,

    val nutrients: String? = null
)