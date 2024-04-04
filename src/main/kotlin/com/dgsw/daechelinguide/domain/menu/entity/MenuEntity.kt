package com.dgsw.daechelinguide.domain.menu.entity

import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import jakarta.persistence.*

@Entity
@Table(name = "menu")
class MenuEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val menu: String? = null,

    val date: String,

    val cal: String? = null,

    val nutrients: String? = null,

    @Enumerated(EnumType.STRING)
    val mealType: MealType
) {

    override fun toString(): String {
        return "MenuEntity(id=$id, menu=$menu, date='$date', cal=$cal, nutrients=$nutrients, mealType=$mealType)"
    }
}