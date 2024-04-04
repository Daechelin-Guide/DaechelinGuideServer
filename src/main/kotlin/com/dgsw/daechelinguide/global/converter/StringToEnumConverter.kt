package com.dgsw.daechelinguide.global.converter

import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import org.springframework.core.convert.converter.Converter

class StringToEnumConverter: Converter<String, MealType> {

    override fun convert(source: String): MealType {
        return MealType.valueOf(source)
    }
}