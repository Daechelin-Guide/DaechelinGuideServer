package com.dgsw.daechelinguide.domain.vote.presentation.dto.request

import java.util.*

data class VoteRequest(
    val mealStartDate: Date,

    val mealEndDate: Date,

    val voteStartDate: Date,

    val voteEndDate: Date
)