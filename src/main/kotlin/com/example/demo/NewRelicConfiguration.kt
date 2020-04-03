package com.example.demo

import com.newrelic.telemetry.Attributes
import io.micrometer.NewRelicRegistryConfig
import io.micrometer.core.instrument.config.MeterFilter
import io.micrometer.core.instrument.util.NamedThreadFactory
import io.micrometer.newrelic.NewRelicRegistry
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration
import org.springframework.boot.actuate.autoconfigure.metrics.export.simple.SimpleMetricsExportAutoConfiguration
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.InetAddress

@Configuration
@AutoConfigureBefore(CompositeMeterRegistryAutoConfiguration::class, SimpleMetricsExportAutoConfiguration::class)
@AutoConfigureAfter(MetricsAutoConfiguration::class)
@ConditionalOnClass(NewRelicRegistry::class)
class NewRelicConfiguration {
    @Bean
    fun newRelicConfig(): NewRelicRegistryConfig {
        return object : NewRelicRegistryConfig {
            override fun get(key: String): String? {
                return null
            }

            override fun apiKey(): String {
                return System.getenv("INSIGHTS_INSERT_KEY")
            }

            override fun serviceName(): String {
                return "SampleService"
            }
        }
    }

    @Bean
    fun newRelicMeterRegistry(config: NewRelicRegistryConfig): NewRelicRegistry {
        val newRelicRegistry = NewRelicRegistry
                .builder(config)
                .commonAttributes(
                        Attributes()
                                .put("host", InetAddress.getLocalHost().hostName)
                                .put("app.name", "sampleApplication")
                ).build()

        newRelicRegistry.config().meterFilter(MeterFilter.denyNameStartsWith("jvm.threads"))
        newRelicRegistry.start(NamedThreadFactory("newrelic.micrometer.registry"))

        return newRelicRegistry
    }
}
