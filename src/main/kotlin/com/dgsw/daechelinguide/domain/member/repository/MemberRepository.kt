package com.dgsw.daechelinguide.domain.member.repository

import com.dgsw.daechelinguide.domain.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
    fun existsByGradeAndRoomAndNumber(grade: Int, room: Int, number: Int): Boolean

    fun findByGradeAndRoomAndNumber(grade: Int, room: Int, number: Int): Member?

    fun findMemberById(memberId: Long): Member?
}