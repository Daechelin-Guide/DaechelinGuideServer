package com.dgsw.daechelinguide.domain.vote.service

import com.dgsw.daechelinguide.domain.member.repository.MemberQueryRepository
import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import com.dgsw.daechelinguide.domain.menu.repository.MenuQueryRepository
import com.dgsw.daechelinguide.domain.ranking.presentation.dto.response.RankingListResponse
import com.dgsw.daechelinguide.domain.ranking.presentation.dto.response.RankingResponse
import com.dgsw.daechelinguide.domain.vote.entity.Vote
import com.dgsw.daechelinguide.domain.vote.entity.VoteInfo
import com.dgsw.daechelinguide.domain.vote.event.VoteMenuUploadEvent
import com.dgsw.daechelinguide.domain.vote.presentation.dto.request.VoteMenuRequest
import com.dgsw.daechelinguide.domain.vote.presentation.dto.request.VoteRequest
import com.dgsw.daechelinguide.domain.vote.presentation.dto.response.VoteListResponse
import com.dgsw.daechelinguide.domain.vote.presentation.dto.response.VoteResponse
import com.dgsw.daechelinguide.domain.vote.repository.VoteInfoRepository
import com.dgsw.daechelinguide.domain.vote.repository.VoteQueryRepository
import com.dgsw.daechelinguide.domain.vote.repository.VoteRepository
import com.dgsw.daechelinguide.global.security.service.SecurityService
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*


@Service
class VoteService(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val securityService: SecurityService,
    private val memberQueryRepository: MemberQueryRepository,
    private val menuQueryRepository: MenuQueryRepository,
    private val voteQueryRepository: VoteQueryRepository,
    private val voteRepository: VoteRepository,
    private val voteInfoRepository: VoteInfoRepository
) {

    fun createVote(request: VoteRequest) {
        val vote = Vote(
            mealStartDate = request.mealStartDate,
            mealEndDate = request.mealEndDate,
            voteStartDate = request.voteStartDate,
            voteEndDate = request.voteEndDate
        )

        voteRepository.save(vote).also {
            applicationEventPublisher.publishEvent(
                VoteMenuUploadEvent(
                    voteId = it.id!!,
                    mealStartDate = formatDate(request.mealStartDate),
                    mealEndDate = formatDate(request.mealEndDate)
                )
            )
        }
    }

    fun getVoteList(): VoteListResponse {
        val vote = voteRepository.findAll()
        val voteResponse = vote.map {
            VoteResponse(
                id = it.id!!,
                mealStartDate = it.mealStartDate,
                mealEndDate = it.mealEndDate,
                voteStartDate = it.voteStartDate,
                voteEndDate = it.voteEndDate,
                voteStatus = it.voteStatus!!
            )
        }

        return VoteListResponse(voteResponse)
    }

    private fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat("yyyyMMdd")
        return formatter.format(date)
    }

    @Cacheable(
        cacheNames = ["vote"],
        key = "#voteId.toString() + #mealType.toString()",
        cacheManager = "contentCacheManager"
    )
    fun getCacheVoteMenu(voteId: Long, mealType: MealType): RankingListResponse {
        val vote = voteQueryRepository.findVoteById(voteId)
            ?: throw RuntimeException("으악")

        val request = VoteMenuUploadEvent(
            voteId = vote.id!!,
            mealStartDate = formatDate(vote.mealStartDate),
            mealEndDate = formatDate(vote.mealEndDate)
        )
        return putCacheVoteMenu(request, mealType)
    }

    @CachePut(
        cacheNames = ["vote"],
        key = "#request.voteId.toString() + #mealType.toString()",
        cacheManager = "contentCacheManager"
    )
    fun putCacheVoteMenu(request: VoteMenuUploadEvent, mealType: MealType): RankingListResponse {
        var index = 1
        val menu = menuQueryRepository.findAllDate(request, mealType)
        val rankingResponse = menu.map {
            RankingResponse(
                id = it.id!!,
                menu = it.menu,
                date = it.date,
                cal = it.cal,
                totalScore = it.totalScore,
                ranking = index++
            )
        }
        return RankingListResponse(rankingResponse)
    }

    fun menuVote(request: VoteMenuRequest, voteId: Long) {
        val member = memberQueryRepository.findMemberById(securityService.getCurrentUserId())
            ?: throw RuntimeException("존재 하지 않음")
        val vote = voteRepository.findVoteById(voteId)
            ?: throw RuntimeException("존재 하지 않음")
        val breakfast = menuQueryRepository.findById(request.breakfastId)
            ?: throw RuntimeException("존재 하지 않음")
        val lunch = menuQueryRepository.findById(request.lunchId)
            ?: throw RuntimeException("존재 하지 않음")
        val dinner = menuQueryRepository.findById(request.dinnerId)
            ?: throw RuntimeException("존재 하지 않음")

        val voteInfo = VoteInfo(
            member = member,
            breakfast = breakfast,
            lunch = lunch,
            dinner = dinner,
            vote = vote
        )

        voteInfoRepository.save(voteInfo)
    }
}