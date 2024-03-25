package com.dgsw.daechelinguide.global.config

import com.dgsw.daechelinguide.global.property.DauthProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        DauthProperties::class
    ]
)
class PropertiesScanConfig