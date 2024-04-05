package com.dgsw.daechelinguide.domain.menu.presentation

import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import com.dgsw.daechelinguide.domain.menu.presentation.dto.MealDetailResponse
import com.dgsw.daechelinguide.domain.menu.presentation.dto.MealResponse
import com.dgsw.daechelinguide.domain.menu.service.MenuService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/menu")
class MenuController(
    private val menuService: MenuService
) {

    @GetMapping
    fun getMenu(@RequestParam(name = "date") date: String): MealResponse {
        return menuService.getMeal(date)
    }

    @GetMapping("/detail")
    fun getMenuDetail(@RequestParam(name = "date") date: String,
                      @RequestParam(name = "mealType") mealType: MealType): MealDetailResponse {
        return menuService.getMealDetail(date, mealType)
    }
}