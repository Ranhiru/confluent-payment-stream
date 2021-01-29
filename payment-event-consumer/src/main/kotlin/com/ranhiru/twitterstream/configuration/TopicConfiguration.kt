package com.ranhiru.twitterstream.configuration

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "topics")
open class TopicConfiguration {
    @JsonProperty("sourceTopicName")
    lateinit var sourceTopicName: String

    @JsonProperty("destinationTopicName")
    lateinit var destinationTopicName: String
}