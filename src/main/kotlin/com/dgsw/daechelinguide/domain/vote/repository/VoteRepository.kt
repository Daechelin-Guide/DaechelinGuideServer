package com.dgsw.daechelinguide.domain.vote.repository

import com.dgsw.daechelinguide.domain.vote.entity.Vote
import org.springframework.data.repository.CrudRepository

interface VoteRepository: CrudRepository<Vote, Long> {
    fun findVoteById(voteId: Long): Vote?
}