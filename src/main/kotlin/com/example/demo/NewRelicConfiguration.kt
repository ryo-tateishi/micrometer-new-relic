package com.example.demo

import io.micrometer.newrelic.NewRelicMeterRegistry
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.boot.actuate.autoconfigure.metrics.export.newrelic.NewRelicProperties
import org.springframework.boot.actuate.autoconfigure.metrics.export.newrelic.NewRelicPropertiesConfigAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NewRelicConfiguration(properties: NewRelicProperties) : NewRelicPropertiesConfigAdapter(properties) {
    override fun apiKey(): String {
        return System.getenv("NEWRELIC_INSIGHT_API_KEY")
    }

    override fun accountId(): String {
        return System.getenv("NEWRELIC_ACCOUNT_ID")
    }

    @Bean
    fun customizer(): MeterRegistryCustomizer<NewRelicMeterRegistry> {
        return MeterRegistryCustomizer<NewRelicMeterRegistry> {
            it.config()
                    .commonTags("app", "sample-app")
                    .commonTags("environment", "local")
        }
    }
}
