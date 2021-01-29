package com.ranhiru.twitterstream.configuration

import com.ranhiru.twitterstream.Payment
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.KStream
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.EnableKafkaStreams
import org.springframework.kafka.config.KafkaStreamsConfiguration

@EnableKafkaStreams
@Configuration
@EnableKafka
open class KafkaConfiguration {
    @Bean
    open fun defaultKafkaStreamsConfig(kafkaProperties: KafkaProperties): KafkaStreamsConfiguration {
        val streamsProperties = kafkaProperties.buildStreamsProperties()
        return KafkaStreamsConfiguration(streamsProperties)
    }

    @Bean
    open fun streamHandler(streamsBuilder: StreamsBuilder, topicConfiguration: TopicConfiguration): KStream<String, Payment> {
        val stream = streamsBuilder.stream<String, Payment>(topicConfiguration.name)
        stream.peek { key, payment ->
            println("Streaming key $key with value $payment")
        }
        return stream
    }
}
