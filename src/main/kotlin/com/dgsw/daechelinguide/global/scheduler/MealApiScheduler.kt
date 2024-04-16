package com.dgsw.daechelinguide.global.scheduler

import com.dgsw.daechelinguide.domain.menu.service.MenuService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class MealApiScheduler(
    private val menuService: MenuService
) {

    @Scheduled(cron = "0 0 0 1 * *", zone = "Asia/Seoul")
    fun scheduledMenu() {
        var currentDate = LocalDate.now()
        val dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val endDate = currentDate.withDayOfMonth(currentDate.lengthOfMonth())

        while (currentDate.isEqual(endDate) || currentDate.isBefore(endDate)) {
            menuService.mealInfo(currentDate.format(dateFormatter))
            currentDate = currentDate.plusDays(1)
        }

    }
}
