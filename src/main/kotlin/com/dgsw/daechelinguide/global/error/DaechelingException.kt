package com.dgsw.daechelinguide.global.error

abstract class DaechelingException(
    val errorProperty: ErrorProperty
): RuntimeException()