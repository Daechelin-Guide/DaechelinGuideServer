package com.dgsw.daechelinguide.domain.member.service

import com.b1nd.dauth.client.response.DAuthUser
import com.dgsw.daechelinguide.domain.member.entity.Member
import com.dgsw.daechelinguide.domain.member.entity.value.Role
import com.dgsw.daechelinguide.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun signUP(userInfo: DAuthUser): Member {

        val member = Member(
            grade = userInfo.grade,
            room = userInfo.room,
            number = userInfo.number,
            name = userInfo.name,
            role = when(userInfo.role) {
                "STUDENT" -> Role.ROLE_STUDENT
                "TEACHER" -> Role.ROLE_TEACHER
                else -> throw RuntimeException("잘못된 enum값입니다.")
            }
        )

        return memberRepository.save(member)
    }

    fun existMember(member: DAuthUser): Boolean {
        return memberRepository.existsByGradeAndRoomAndNumber(
            member.grade,
            member.room,
            member.number
        )
    }

    fun getUser(member: DAuthUser): Member {
        return memberRepository.findByGradeAndRoomAndNumber(
            member.grade,
            member.room,
            member.number
        ) ?: throw RuntimeException()
    }
}