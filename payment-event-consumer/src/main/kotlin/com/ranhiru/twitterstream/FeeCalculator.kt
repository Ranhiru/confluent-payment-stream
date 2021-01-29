package com.ranhiru.twitterstream

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

class FeeCalculator {
    companion object {
        fun calculateFees(payment: Payment): Payment {
            val feeAmount = BigDecimal((0..100000).random().toDouble() * Random.nextFloat())
                .setScale(2, RoundingMode.HALF_EVEN)
                .toDouble()
            val currency = payment.getAmount().getCurrency()
            val fees = Currency(feeAmount, currency)

            return Payment.newBuilder(payment)
                .setFees(fees)
                .build()
        }
    }
}