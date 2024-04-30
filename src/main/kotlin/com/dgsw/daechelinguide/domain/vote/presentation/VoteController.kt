package com.dgsw.daechelinguide.domain.vote.presentation

import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import com.dgsw.daechelinguide.domain.ranking.presentation.dto.response.RankingListResponse
import com.dgsw.daechelinguide.domain.vote.presentation.dto.response.VoteListResponse
import com.dgsw.daechelinguide.domain.vote.presentation.dto.request.VoteMenuRequest
import com.dgsw.daechelinguide.domain.vote.presentation.dto.request.VoteRequest
import com.dgsw.daechelinguide.domain.vote.service.VoteService
import io.lettuce.core.dynamic.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vote")
class VoteController(
    private val voteService: VoteService
) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createVote(@RequestBody @Value request: VoteRequest) {
        voteService.createVote(request)
    }

    @GetMapping("/list")
    fun getVoteList(): VoteListResponse {
        return voteService.getVoteList()
    }

    @GetMapping("/menu/ranking/{voteId}")
    fun getVoteRanking(
        @PathVariable(name = "voteId") voteId: Long,
        @RequestParam mealType: MealType
    ): RankingListResponse {
        return voteService.getCacheVoteMenu(voteId, mealType)
    }

    @PostMapping("/menu/{voteId}")
    fun menuVote(
        @PathVariable(name = "voteId") voteId: Long,
        @RequestBody request: VoteMenuRequest
    ) {
        return voteService.menuVote(request, voteId)
    }
}