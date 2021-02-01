# Apache Kafka Streams + Spring Boot App


This is an example Spring Boot app that uses Apache Kafka to read payment events from one Kafka topic, adds fees to the payment and
writes it back to a new topic.

The project contains 3 modules

## Consumer

Apache Kafka Streams + Spring Boot application that reads from a `payments` (configurable) topic, adds fees to the `payment` object and writes it back to `updated_payments` topic.

## Producer

Produces random `Payment` events that is to be used for testing.

## Schemas

This module contains `Payment` and `Currency` [Avro IDL](https://avro.apache.org/docs/current/idl.html) files that is used when both consuming and producing payments.

### How to Run

1. Create a new Confluent Cloud account create a new cluster
2. Generate API keys for both Kafka API and Schema Registry API
3. Add the following authorisation to the generated Kafka API access keys
   1. **Cluster**: `kafka-cluster` - `ALLOW` - `IDEMPOTENT_WRITE`
   2. **Consumer Group ID**: `confluent-payment-stream` - `ALLOW` - `READ`
   3. **Topic**: `*` - `ALLOW` - `READ` - This gives read access to all topics in the cluster (You should not do this in a production environment)
   4. **Topic**: `*` - `ALLOW` - `WRITE` - This gives write access to all topics in the cluster (You should not do this in a production environment)
4. Create two topics in Confluent named `payments` and `updated_payments`
5. Set the following environment variables in your shell OR if you are running through IntelliJ, in the launch configuration

```shell
export BOOTSTRAP_SERVERS="$bootstrapServers"
export SCHEMA_REGISTRY_URL="$schemaRegistryUrl"
export BASIC_AUTH_USER_INFO="$SchemaRegistryApiKey:$schemaRegistryApiSecret"
export SASL_JAAS_CONFIG="org.apache.kafka.common.security.plain.PlainLoginModule required username=\"$kafkaApiKey\" password=\"$kafkaApiSecret\";"
```

4. Run `./gradlew payment-event-producer:run` to by default produce 5 payment records. You can pass `--args=$numberOfEvents`  to generate any number of payment events.
   E.g `./gradlew payment-event-producer:run --args="10"`
5. Run `./gradlew payment-event-consumer:run` to start the consumer. The consumer will print the payment event before and after adding fees.
