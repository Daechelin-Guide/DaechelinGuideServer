package com.dgsw.daechelinguide.global.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "neis", url = "https://open.neis.go.kr/hub")
interface NeisClient {

    @GetMapping("/mealServiceDietInfo?Type=json")
    fun neisMealInfo(
        @RequestParam(name = "Key") key: String,
        @RequestParam(name = "ATPT_OFCDC_SC_CODE") aCode: String,
        @RequestParam(name = "SD_SCHUL_CODE") schoolCode: String,
        @RequestParam(name = "MLSV_YMD") date: String,
    ): String
}