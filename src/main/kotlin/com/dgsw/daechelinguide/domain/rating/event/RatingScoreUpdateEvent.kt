package com.dgsw.daechelinguide.domain.rating.event

import com.dgsw.daechelinguide.domain.menu.entity.Menu

data class RatingScoreUpdateEvent(
    val menu: Menu,
    val score: Double
)
