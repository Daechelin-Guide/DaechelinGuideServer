package com.dgsw.daechelinguide.global.scheduler

import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import com.dgsw.daechelinguide.domain.ranking.service.RankingService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class RankingApiScheduler(
    private val rankingService: RankingService
) {

    @Scheduled(cron = "0 0/30 * * * ?", zone = "Asia/Seoul")
    fun scheduledMenu() {
        rankingService.ranking(MealType.TYPE_BREAKFAST)
        rankingService.ranking(MealType.TYPE_LUNCH)
        rankingService.ranking(MealType.TYPE_DINNER)
    }
}