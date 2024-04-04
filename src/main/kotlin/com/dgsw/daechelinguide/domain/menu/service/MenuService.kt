package com.dgsw.daechelinguide.domain.menu.service

import com.dgsw.daechelinguide.domain.menu.entity.MenuEntity
import com.dgsw.daechelinguide.domain.menu.entity.value.MealType
import com.dgsw.daechelinguide.domain.menu.presentation.dto.MealResponse
import com.dgsw.daechelinguide.domain.menu.repository.MenuRepository
import com.dgsw.daechelinguide.global.feign.NeisClient
import com.dgsw.daechelinguide.global.property.NeisProperties
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.stereotype.Service
import java.lang.NullPointerException
import kotlin.RuntimeException

@Service
class MenuService(
    private val menuRepository: MenuRepository,
    private val neisClient: NeisClient,
    private val neisProperties: NeisProperties
) {

    fun getMeal(date: String): MealResponse {
        val meal = menuRepository.findAllByDate(date)
        var breakfast: String? = null
        var lunch: String? = null
        var dinner: String? = null

        meal.map {
            when(it.mealType) {
                MealType.TYPE_BREAKFAST -> breakfast = it.menu
                MealType.TYPE_LUNCH -> lunch = it.menu
                MealType.TYPE_DINNER -> dinner = it.menu
            }
        }

        return MealResponse(breakfast, lunch, dinner)
    }

    fun getMealDetail(date: String, mealType: MealType): MenuEntity {
        println(mealType)
        val meal = menuRepository.findByDateAndMealType(date, mealType)
            ?: throw RuntimeException("으아")

        println(meal.toString())

        return meal
    }

    fun mealInfo(date: String) {
        val meal = neisClient.neisMealInfo(
            neisProperties.key,
            neisProperties.educationCode,
            neisProperties.schoolCode,
            date
        )

        val breakfast = getMenuEntity(date, meal, MealType.TYPE_BREAKFAST)
        val lunch = getMenuEntity(date, meal, MealType.TYPE_LUNCH)
        val dinner = getMenuEntity(date, meal, MealType.TYPE_DINNER)

        menuRepository.saveAll(listOf(breakfast, lunch, dinner))
    }

    private fun getMenuEntity(date: String, meal: String, mealType: MealType): MenuEntity {
        val menuEntity = MenuEntity(
            menu = null,
            date = date,
            cal = null,
            nutrients = null,
            mealType = mealType
        )

        try {
            val parser = JSONParser()
            val jsonObject = parser.parse(meal) as JSONObject
            val array = jsonObject["mealServiceDietInfo"] as JSONArray

            val objectArray = array[1] as JSONObject
            val row = objectArray["row"] as JSONArray

            for (value in row) {
                val mealJson = value as JSONObject
                val mealName = mealJson["MMEAL_SC_NM"] as String

                if (mealType.type == mealName) {
                    val nutrients = (mealJson["NTR_INFO"] as String)
                        .replace("<br/>", ", ")
                    val cal = mealJson["CAL_INFO"] as String
                    val mealMenu = (mealJson["DDISH_NM"] as String)
                        .replace(Regex("\\([^)]*\\)|<br/>"), "")
                        .trim().replace(Regex("\\s{2}"), ", ")
                        .replace("1", "")

                    return MenuEntity(
                        menu = mealMenu,
                        date = date,
                        cal = cal,
                        nutrients = nutrients,
                        mealType = mealType
                    )
                }
            }
        } catch (e: NullPointerException) {
            return menuEntity
        } catch (e: Exception) {
            throw RuntimeException("나이스 병신")
        }

        return menuEntity
    }
}