package com.dgsw.daechelinguide.domain.menu.repository

import com.dgsw.daechelinguide.domain.menu.entity.Menu
import com.dgsw.daechelinguide.domain.menu.entity.QMenu
import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import com.dgsw.daechelinguide.domain.vote.event.VoteMenuUploadEvent
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class MenuQueryRepository(
    private val jpaQueryFactory: JPAQueryFactory
) {

    fun findAllByDate(date: String): List<Menu> {
        val entity = QMenu.menu1

        return jpaQueryFactory.selectFrom(entity)
            .where(entity.date.eq(date))
            .fetch()
    }

    fun findByDateAndMealType(date: String, mealType: MealType): Menu? {
        val entity = QMenu.menu1

        return jpaQueryFactory.selectFrom(entity)
            .where(
                entity.date.eq(date),
                entity.mealType.eq(mealType)
            )
            .fetchOne()

    }

    fun findById(menuId: Long): Menu? {
        val entity = QMenu.menu1

        return jpaQueryFactory.selectFrom(entity)
            .where(entity.id.eq(menuId))
            .fetchOne()
    }

    fun findAllMenuOrderByDesc(mealType: MealType): List<Menu> {
        val entity = QMenu.menu1

        return jpaQueryFactory.selectFrom(entity)
            .where(entity.menu.isNotNull)
            .where(entity.mealType.eq(mealType))
            .orderBy(entity.totalScore.desc())
            .limit(10)
            .fetch()
    }

    fun findAllDate(request: VoteMenuUploadEvent, mealType: MealType): List<Menu> {
        val entity = QMenu.menu1

        return jpaQueryFactory.selectFrom(entity)
            .where(entity.date.stringValue().between(request.mealStartDate, request.mealEndDate))
            .where(entity.mealType.eq(mealType))
            .orderBy(entity.totalScore.desc())
            .fetch()
    }
}