package com.dgsw.daechelinguide.domain.rating.domain

import com.dgsw.daechelinguide.domain.member.entity.Member
import com.dgsw.daechelinguide.domain.menu.entity.Menu
import jakarta.persistence.*

@Entity
@Table(name = "rating")
class Rating(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val score: Double,
    val comment: String? = null,
    @ManyToOne
    val menu: Menu,
    @OneToOne
    val member: Member
)