package com.dgsw.daechelinguide.domain.rating.event

import com.dgsw.daechelinguide.domain.menu.repository.MenuRepository
import com.dgsw.daechelinguide.domain.rating.repository.RatingRepositoryCustom
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class RatingEventListener(
    private val menuRepository: MenuRepository,
    private val ratingRepositoryCustom: RatingRepositoryCustom
) {
    @Async
    @EventListener
    fun onMenuScoreCalculationHandler(event: RatingScoreUpdateEvent) {
        val totalScore = ratingRepositoryCustom.queryScoreAvg(event.menu.id!!)
        val menu = event.menu
        menu.totalScore = totalScore
        menuRepository.save(menu)
    }
}