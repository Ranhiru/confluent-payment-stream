package com.ranhiru.twitterstream

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class PaymentEventConsumerApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(PaymentEventConsumerApplication::class.java, *args)
        }
    }
}
