package com.dgsw.daechelinguide.domain.menu.entity

import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import jakarta.persistence.*

@Entity
@Table(name = "menu")
class Menu(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val menu: String? = null,

    val date: String,

    val cal: String? = null,

    val nutrients: String? = null,

    var totalScore: Double? = 0.0,

    @Enumerated(EnumType.STRING)
    val mealType: MealType
)