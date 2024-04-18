package com.dgsw.daechelinguide.domain.ranking.presentation

import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import com.dgsw.daechelinguide.domain.ranking.presentation.dto.response.RankingListResponse
import com.dgsw.daechelinguide.domain.ranking.service.RankingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ranking")
class RankingController(
    private val rankingService: RankingService
) {

    @GetMapping
    fun ranking(@RequestParam(name = "mealType") mealType: MealType): RankingListResponse {
        return rankingService.ranking(mealType)
    }
}