package com.ranhiru.twitterstream.configuration

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "topic")
open class TopicConfiguration {
    @JsonProperty("name")
    lateinit var name: String
}