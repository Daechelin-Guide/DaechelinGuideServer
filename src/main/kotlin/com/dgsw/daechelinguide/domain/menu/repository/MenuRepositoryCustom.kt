package com.dgsw.daechelinguide.domain.menu.repository

import com.dgsw.daechelinguide.domain.menu.entity.Menu
import com.dgsw.daechelinguide.domain.menu.entity.QMenu
import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class MenuRepositoryCustom(
    private val jpaQueryFactory: JPAQueryFactory
) {

    fun findAllMenuOrderByAsc(mealType: MealType): List<Menu> {
        val entity = QMenu.menu1

        return jpaQueryFactory.selectFrom(entity)
            .where(entity.menu.isNotNull)
            .where(entity.mealType.eq(mealType))
            .orderBy(entity.totalScore.desc())
            .limit(10)
            .fetch()
    }
}