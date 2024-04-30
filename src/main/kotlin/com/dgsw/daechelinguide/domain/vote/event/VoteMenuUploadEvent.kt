package com.dgsw.daechelinguide.domain.vote.event

data class VoteMenuUploadEvent(
    val voteId: Long,

    val mealStartDate: String,

    val mealEndDate: String
)