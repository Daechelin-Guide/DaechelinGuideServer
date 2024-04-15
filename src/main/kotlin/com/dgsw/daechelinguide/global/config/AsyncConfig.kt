package com.dgsw.daechelinguide.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import java.util.concurrent.Executor

@EnableAsync
@Configuration
class AsyncConfig: AsyncConfigurer {

    override fun getAsyncExecutor(): Executor? {
        return super.getAsyncExecutor()
    }
}