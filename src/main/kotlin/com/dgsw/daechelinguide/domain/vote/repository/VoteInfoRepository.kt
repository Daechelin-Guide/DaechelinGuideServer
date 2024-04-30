package com.dgsw.daechelinguide.domain.vote.repository

import com.dgsw.daechelinguide.domain.vote.entity.VoteInfo
import com.dgsw.daechelinguide.domain.vote.entity.embed.VoteInfoId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VoteInfoRepository: CrudRepository<VoteInfo, VoteInfoId>