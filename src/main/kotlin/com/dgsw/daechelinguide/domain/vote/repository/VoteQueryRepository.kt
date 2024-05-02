package com.dgsw.daechelinguide.domain.vote.repository

import com.dgsw.daechelinguide.domain.vote.entity.QVote
import com.dgsw.daechelinguide.domain.vote.entity.Vote
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class VoteQueryRepository(
    private val jpaQueryFactory: JPAQueryFactory
) {

    fun findVoteById(voteId: Long): Vote?{
        val entity = QVote.vote

        return jpaQueryFactory.selectFrom(entity)
            .where(entity.id.eq(voteId))
            .fetchOne()
    }
}