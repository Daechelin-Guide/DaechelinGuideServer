package com.dgsw.daechelinguide.domain.rating.repository

import com.dgsw.daechelinguide.domain.rating.domain.QRating
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class RatingRepositoryCustom(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun queryScoreAvg(menuId: Long): Double? {
        val ratingEntity = QRating.rating

        return jpaQueryFactory.query()
            .select(ratingEntity.score.avg())
            .from(ratingEntity)
            .fetchOne()
    }
}