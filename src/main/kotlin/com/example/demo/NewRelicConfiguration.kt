package com.example.demo

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NewRelicConfiguration {
    @Bean
    fun customizer(): MeterRegistryCustomizer<MeterRegistry> {
        return MeterRegistryCustomizer<MeterRegistry> {
            it.config().commonTags(
                    "appName", "ioMicrometerApp",
                    "environment", "local-for-ioMicrometer"
            )
        }
    }
}
