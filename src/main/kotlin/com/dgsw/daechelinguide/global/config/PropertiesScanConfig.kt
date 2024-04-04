package com.dgsw.daechelinguide.global.config

import com.dgsw.daechelinguide.global.property.DauthProperties
import com.dgsw.daechelinguide.global.property.JwtProperties
import com.dgsw.daechelinguide.global.property.NeisProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        DauthProperties::class,
        JwtProperties::class,
        NeisProperties::class
    ]
)
class PropertiesScanConfig