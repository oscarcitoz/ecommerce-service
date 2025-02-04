package com.fluxi.order.responses

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import java.math.BigDecimal
import java.time.LocalDateTime

@Serdeable
@Introspected
class OrderDashboard {
    var summary: Summary = Summary()
    var orders: List<OrderSummary> = listOf()
}

@Serdeable
@Introspected
class Summary {
    @field:JsonProperty("total_value")
    var totalValue: BigDecimal = BigDecimal.ZERO

    @field:JsonProperty("total_value_with_discount")
    var totalValueWithDiscount: BigDecimal = BigDecimal.ZERO

    @field:JsonProperty("total_units")
    var totalUnits: Int = 0

    @field:JsonProperty("total_buyers")
    var totalBuyers: Int = 0
}

@Serdeable
@Introspected
class OrderSummary {
    var id: Long = 0

    @field:JsonProperty("hash_id")
    var hashId: String = ""

    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @field:JsonProperty("total_value")
    var totalValue: BigDecimal = BigDecimal.ZERO

    @field:JsonProperty("total_value_with_discount")
    var totalValueWithDiscount: BigDecimal = BigDecimal.ZERO
    @field:JsonProperty("customer_name")
    var customerName: String = ""
    @field:JsonProperty("customer_las_name")
    var customerLastName: String = ""
    @field:JsonProperty("customer_prefix_phone")
    var customerPrefixPhone: String = ""
    @field:JsonProperty("customer_phone")
    var customerPhone: String = ""
    @field:JsonProperty("customer_email")
    var customerEmail: String = ""
    @field:JsonProperty("customer_address")
    var customerAddress: String = ""
    @field:JsonProperty("total_units")
    var totalUnits: Int = 0
}