package com.ranhiru.twitterstream

import io.confluent.kafka.schemaregistry.client.SchemaRegistryClientConfig.BASIC_AUTH_CREDENTIALS_SOURCE
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClientConfig.USER_INFO_CONFIG
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG
import org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG
import org.apache.kafka.clients.CommonClientConfigs.SECURITY_PROTOCOL_CONFIG
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.config.SaslConfigs.SASL_JAAS_CONFIG
import org.apache.kafka.common.config.SaslConfigs.SASL_MECHANISM

object Config {
    fun getAuthConfig(): Map<String, String> {
        val saslJaasConfig = System.getenv("SASL_JAAS_CONFIG")

        return mapOf(
            SECURITY_PROTOCOL_CONFIG to "SASL_SSL",
            SASL_JAAS_CONFIG to saslJaasConfig,
            SASL_MECHANISM to "PLAIN"
        )
    }

    fun getSchemaRegistryConfig(): Map<String, String> {
        val schemaRegistryUrl = System.getenv("SCHEMA_REGISTRY_URL")
        val basicAuthUserInfo = System.getenv("BASIC_AUTH_USER_INFO")

        return mapOf(
            SCHEMA_REGISTRY_URL_CONFIG to schemaRegistryUrl,
            USER_INFO_CONFIG to basicAuthUserInfo,
            BASIC_AUTH_CREDENTIALS_SOURCE to "USER_INFO",
            SECURITY_PROTOCOL_CONFIG to "SASL_SSL"
        )
    }

    fun getBoostrapServerConfig(): Map<String, String> {
        val bootStrapServers = System.getenv("BOOTSTRAP_SERVERS")
        return mapOf(
            BOOTSTRAP_SERVERS_CONFIG to bootStrapServers
        )
    }

    fun getProducerConfig(): Map<String, String> {
        return mapOf(
            ProducerConfig.ACKS_CONFIG to "all",
            ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG to "true",
            ProducerConfig.COMPRESSION_TYPE_CONFIG to "snappy",
            ProducerConfig.LINGER_MS_CONFIG to "20",
            ProducerConfig.BATCH_SIZE_CONFIG to "32768"
        )
    }
}
