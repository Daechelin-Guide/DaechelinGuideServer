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

    fun findRatingCommentByMenuId(menuId: Long): List<String> {
        val ratingEntity = QRating.rating

        return jpaQueryFactory.query()
            .select(ratingEntity.comment)
            .from(ratingEntity)
            .where(ratingEntity.comment.isNotNull)
            .where(ratingEntity.menu.id.eq(menuId))
            .fetch()
    }
}