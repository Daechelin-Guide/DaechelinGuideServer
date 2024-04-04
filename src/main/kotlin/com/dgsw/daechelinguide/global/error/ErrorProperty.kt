package com.dgsw.daechelinguide.global.error

interface ErrorProperty {

    fun status(): Int

    fun message(): String

    fun code(): String

    fun formatMessage(vararg dates: String): ErrorProperty {
        return this.let {
            object : ErrorProperty {
                override fun status(): Int = it.status()
                override fun message(): String = it.message().format(*dates)
                override fun code(): String = it.code()
            }
        }
    }

}