package com.fluxi.order.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fluxi.core.constants.DATE_STRING_FORMAT
import io.hypersistence.utils.hibernate.type.json.JsonType
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import org.hibernate.annotations.Type
import java.math.BigDecimal
import java.time.LocalDateTime

@Serdeable
@Entity
@Table(name = "order_product")
@PersistenceContext(name = "order")
class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_product_seq_generator")
    @SequenceGenerator(name = "order_product_seq_generator", sequenceName = "order_product_id_seq", allocationSize = 1)
    @field:JsonProperty("id")
    var id: Long? = null

    @Column(name = "order_id", nullable = false)
    @field:JsonProperty("order_id")
    var orderId: Long = 0L

    @Column(name = "product_id", nullable = false)
    @field:JsonProperty("product_id")
    var productId: String = ""

    @Column(name = "name", nullable = false)
    @field:JsonProperty("name")
    var name: String = ""

    @Type(JsonType::class)
    @Column(name = "images", nullable = false, columnDefinition = "jsonb")
    @field:JsonProperty("images")
    var images: List<String> = listOf()

    @Column(name = "units", nullable = false)
    @field:JsonProperty("units")
    var units: Int = 0

    @Column(name = "unit_price", nullable = false)
    @field:JsonProperty("unit_price")
    var unitPrice: BigDecimal = BigDecimal.ZERO

    @Column(name = "unit_price_with_discount", nullable = false)
    @field:JsonProperty("unit_price_with_discount")
    var unitPriceWithDiscount: BigDecimal = BigDecimal.ZERO

    @Column(name = "total_value", nullable = false)
    @field:JsonProperty("total_value")
    var totalValue: BigDecimal = BigDecimal.ZERO

    @JsonFormat(pattern= DATE_STRING_FORMAT)
    @Column(name = "created_at", nullable = false)
    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @JsonFormat(pattern= DATE_STRING_FORMAT)
    @Column(name = "updated_at", nullable = false)
    @field:JsonProperty("updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "customer_comments")
    @field:JsonProperty("customer_comments")
    var customerComments: String? = null

    @Column(name = "total_value_with_discount")
    @field:JsonProperty("total_value_with_discount")
    var totalValueWithDiscount: BigDecimal = BigDecimal.ZERO
} 