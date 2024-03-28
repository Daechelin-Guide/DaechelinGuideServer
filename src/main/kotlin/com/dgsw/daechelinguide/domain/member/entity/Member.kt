package com.dgsw.daechelinguide.domain.member.entity

import com.dgsw.daechelinguide.domain.member.entity.value.Role
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val grade: Int,

    val room: Int,

    val number: Int,

    val name: String,

    val profileImage: String? = null,

    @Enumerated(EnumType.STRING)
    val role: Role
)