package com.fluxi.offer.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.time.LocalDateTime

@Serdeable
@Entity
@Table(name = "offer_customer")
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

    @Column(name = "customer_id", nullable = false)
    @field:JsonProperty("customer_id")
    var customerId: String = ""

    @Column(name = "enabled", nullable = false)
    @field:JsonProperty("enabled")
    var enabled: Boolean = false

    @Column(name = "created_at", nullable = false)
    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "starts_at", nullable = false)
    @field:JsonProperty("starts_at")
    var startsAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "ends_at", nullable = false)
    @field:JsonProperty("ends_at")
    var endsAt: LocalDateTime = LocalDateTime.now()
} 