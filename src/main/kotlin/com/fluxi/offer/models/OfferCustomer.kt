package com.fluxi.offer.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fluxi.core.constants.DATE_STRING_FORMAT
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Serdeable
@Entity
@Table(name = "offer_customer")
@PersistenceContext(name = "offer")
class OfferCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offer_customer_seq_generator")
    @SequenceGenerator(
        name = "offer_customer_seq_generator",
        sequenceName = "offer_customer_id_seq",
        allocationSize = 1
    )
    @field:JsonProperty("id")
    var id: Long? = null

    @Column(name = "offer_id", nullable = false)
    @field:JsonProperty("offer_id")
    var offerId: Long = 0L

    @Column(name = "order_id", nullable = false)
    @field:JsonProperty("order_id")
    var orderId: Long = 0L

    @Column(name = "product_id", nullable = false)
    @field:JsonProperty("product_id")
    var productId: String = ""

    @Column(name = "discount_type", nullable = false)
    @field:JsonProperty("discount_type")
    var discountType: String = ""

    @Column(name = "discount_value", nullable = false)
    @field:JsonProperty("discount_value")
    var discountValue: BigDecimal = BigDecimal.ZERO

    @Column(name = "enabled", nullable = false)
    @field:JsonProperty("enabled")
    var enabled: Boolean = true

    @Column(name = "created_at", nullable = false)
    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at", nullable = false)
    @field:JsonProperty("updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()

    @JsonFormat(pattern = DATE_STRING_FORMAT)
    @Column(name = "starts_at", nullable = false)
    @field:JsonProperty("starts_at")
    var startsAt: LocalDateTime = LocalDateTime.now()

    @JsonFormat(pattern = DATE_STRING_FORMAT)
    @Column(name = "ends_at", nullable = false)
    @field:JsonProperty("ends_at")
    var endsAt: LocalDateTime = LocalDateTime.now()
} 