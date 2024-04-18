package com.dgsw.daechelinguide.domain.ranking.service

import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import com.dgsw.daechelinguide.domain.menu.repository.MenuRepositoryCustom
import com.dgsw.daechelinguide.domain.ranking.presentation.dto.response.RankingListResponse
import com.dgsw.daechelinguide.domain.ranking.presentation.dto.response.RankingResponse
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class RankingService(
    private val menuRepositoryCustom: MenuRepositoryCustom
) {

    @Cacheable(cacheNames = ["ranking"], key = "#mealType", cacheManager = "contentCacheManager")
    fun ranking(mealType: MealType): RankingListResponse {
        return getRanking(mealType)
    }


    @CachePut(cacheNames = ["ranking"], key = "#mealType", cacheManager = "contentCacheManager")
    fun getRanking(mealType: MealType): RankingListResponse {
        var index = 1
        val menu = menuRepositoryCustom.findAllMenuOrderByAsc(mealType)

        val rankingResponse= menu.map {
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
}