package com.dgsw.daechelinguide.domain.menu.repository

import com.dgsw.daechelinguide.domain.menu.entity.Menu
import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import org.springframework.data.repository.CrudRepository

interface MenuRepository: CrudRepository<Menu, Long>