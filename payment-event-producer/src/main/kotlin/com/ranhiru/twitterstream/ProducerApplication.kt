package com.ranhiru.twitterstream

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random.Default.nextFloat

@SpringBootApplication
open class ProducerApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ProducerApplication::class.java, *args)
        }

        @Bean
        fun demo(): CommandLineRunner {
            return CommandLineRunner {
                val payment = generatePaymentEvent()
                val confluentProducer = ConfluentProducer()
                confluentProducer.produceRecord(payment)
            }
        }

        private fun generatePaymentEvent(): Payment {
            val id = (0..10000000000000).random().toString()
            val currency = listOf("AUD", "USD", "NZD", "LKR", "CAD", "SGD").random()
            val amount = BigDecimal((0..100000).random().toDouble() * nextFloat())
                .setScale(2, RoundingMode.HALF_EVEN)
                .toDouble()

            return Payment.newBuilder()
                .setId(id)
                .setAmount(Currency(amount, currency))
                .build()
        }
    }
}
