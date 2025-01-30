package com.fluxi.order.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fluxi.core.constants.DATE_STRING_FORMAT
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Serdeable
@Entity
@Table(name = "order_offer")
@PersistenceContext(name = "order")
class OrderOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_offer_seq_generator")
    @SequenceGenerator(name = "order_offer_seq_generator", sequenceName = "order_offer_id_seq", allocationSize = 1)
    @field:JsonProperty("id")
    var id: Long? = null

    @Column(name = "order_product_id", nullable = false)
    @field:JsonProperty("order_product_id")
    var orderProductId: Long = 0L

    @Column(name = "order_id", nullable = false)
    @field:JsonProperty("order_id")
    var orderId: Long = 0L

    @Column(name = "offer_id", nullable = false)
    @field:JsonProperty("offer_id")
    var offerId: Long = 0L

    @JsonFormat(pattern= DATE_STRING_FORMAT)
    @Column(name = "starts_at", nullable = false)
    @field:JsonProperty("starts_at")
    var startsAt: LocalDateTime = LocalDateTime.now()

    @JsonFormat(pattern=DATE_STRING_FORMAT)
    @Column(name = "ends_at", nullable = false)
    @field:JsonProperty("ends_at")
    var endsAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "created_at", nullable = false)
    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "discount_type", nullable = false)
    @field:JsonProperty("discount_type")
    var discountType: String = ""

    @Column(name = "discount_value", nullable = false)
    @field:JsonProperty("discount_value")
    var discountValue: BigDecimal = BigDecimal.ZERO

    @Column(name = "discount_applied", nullable = false)
    @field:JsonProperty("discount_applied")
    var discountApplied: BigDecimal = BigDecimal.ZERO
} 