package com.dgsw.daechelinguide.domain.vote.presentation.dto.response

import com.dgsw.daechelinguide.domain.vote.entity.value.VoteStatus
import java.util.*

data class VoteResponse(
    val id: Long,

    val mealStartDate: Date,

    val mealEndDate: Date,

    val voteStartDate: Date,

    val voteEndDate: Date,

    val voteStatus: VoteStatus
)

data class VoteListResponse(
    val voteResponse: List<VoteResponse>
)