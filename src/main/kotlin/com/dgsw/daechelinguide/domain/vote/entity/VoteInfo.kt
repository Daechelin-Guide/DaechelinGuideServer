package com.dgsw.daechelinguide.domain.vote.entity

import com.dgsw.daechelinguide.domain.member.entity.Member
import com.dgsw.daechelinguide.domain.menu.entity.Menu
import com.dgsw.daechelinguide.domain.vote.entity.embed.VoteInfoId
import jakarta.persistence.*

@Entity
@Table(name = "vote_info")
@IdClass(VoteInfoId::class)
class VoteInfo (
    @Id
    @ManyToOne(optional = false, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "member_id")
    val member: Member,

    @Id
    @ManyToOne(optional = false, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "breakfast_id")
    val breakfast: Menu,

    @Id
    @ManyToOne(optional = false, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "lunch_id")
    val lunch: Menu,

    @Id
    @ManyToOne(optional = false, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "dinner_id")
    val dinner: Menu,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    val vote: Vote
)