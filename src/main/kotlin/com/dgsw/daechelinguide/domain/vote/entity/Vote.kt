package com.dgsw.daechelinguide.domain.vote.entity

import com.dgsw.daechelinguide.domain.vote.entity.value.VoteStatus
import jakarta.persistence.*
import java.util.*
import kotlin.collections.ArrayList

@Entity
@Table(name = "vote")
class Vote (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val mealStartDate: Date,

    val mealEndDate: Date,

    val voteStartDate: Date,

    val voteEndDate: Date,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vote", orphanRemoval = true)
    val voteInfo: List<VoteInfo> = ArrayList(),

    @Enumerated(EnumType.STRING)
    val voteStatus: VoteStatus? = VoteStatus.ONGOING
)