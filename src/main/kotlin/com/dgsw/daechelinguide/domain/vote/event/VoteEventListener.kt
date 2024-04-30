package com.dgsw.daechelinguide.domain.vote.event

import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import com.dgsw.daechelinguide.domain.vote.service.VoteService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class VoteEventListener(
    private val voteService: VoteService
) {
    @EventListener
    fun execute(event: VoteMenuUploadEvent) {
        voteService.putCacheVoteMenu(event, MealType.TYPE_BREAKFAST)
        voteService.putCacheVoteMenu(event, MealType.TYPE_LUNCH)
        voteService.putCacheVoteMenu(event, MealType.TYPE_DINNER)
    }
}
