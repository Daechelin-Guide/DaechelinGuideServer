package com.dgsw.daechelinguide.domain.menu.repository

import com.dgsw.daechelinguide.domain.menu.entity.MenuEntity
import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import org.springframework.data.repository.CrudRepository

interface MenuRepository: CrudRepository<MenuEntity, Long> {
    fun findAllByDate(date: String): List<MenuEntity>

    fun findByDateAndMealType(date: String, mealType: MealType): MenuEntity?
}