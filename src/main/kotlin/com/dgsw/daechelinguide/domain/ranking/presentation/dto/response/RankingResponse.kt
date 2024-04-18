package com.dgsw.daechelinguide.domain.ranking.presentation.dto.response

data class RankingResponse(
    val id: Long = 0,
    val menu: String? = null,
    val date: String = "",
    val cal: String? = null,
    var totalScore: Double? = 0.0,
    val ranking: Int = 0
)
