package com.dgsw.daechelinguide.domain.member.repository

import com.dgsw.daechelinguide.domain.member.entity.Member
import com.dgsw.daechelinguide.domain.member.entity.QMember
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class MemberQueryRepository(
    private val jpaQueryFactory: JPAQueryFactory
) {

    fun findMemberById(memberId: Long): Member? {
        val entity = QMember.member

        return jpaQueryFactory.selectFrom(entity)
            .where(entity.id.eq(memberId))
            .fetchOne()
    }

    fun findByGradeAndRoomAndNumber(grade: Int, room: Int, number: Int): Member? {
        val entity = QMember.member

        return jpaQueryFactory.selectFrom(entity)
            .where(
                entity.grade.eq(grade),
                entity.room.eq(room),
                entity.number.eq(number)
            ).fetchOne()
    }

    fun existsByGradeAndRoomAndNumber(grade: Int, room: Int, number: Int): Boolean {
        val entity = QMember.member

        return jpaQueryFactory.selectFrom(entity)
            .where(
                entity.grade.eq(grade),
                entity.room.eq(room),
                entity.number.eq(number)
            ).fetchFirst() != null
    }
}