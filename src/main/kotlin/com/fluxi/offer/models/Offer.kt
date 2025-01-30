package com.fluxi.offer.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Serdeable
@Entity
@Table(name = "offers")
@PersistenceContext(name = "offer")
class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offer_seq_generator")
    @SequenceGenerator(name = "offer_seq_generator", sequenceName = "offers_id_seq", allocationSize = 1)
    @field:JsonProperty("id")
    var id: Long? = null

    @Column(name = "name", nullable = false)
    @field:JsonProperty("name")
    var name: String = ""

    @Column(name = "description", nullable = false)
    @field:JsonProperty("description")
    var description: String = ""

    @Column(name = "enabled", nullable = false)
    @field:JsonProperty("enabled")
    var enabled: Boolean = false

    @Column(name = "created_at", nullable = false)
    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at", nullable = false)
    @field:JsonProperty("updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "discount_type", nullable = false)
    @field:JsonProperty("discount_type")
    var discountType: String = ""

    @Column(name = "discount_value", nullable = false)
    @field:JsonProperty("discount_value")
    var discountValue: BigDecimal = BigDecimal.ZERO

    @Column(name = "owner_id", nullable = false)
    @field:JsonProperty("owner_id")
    var ownerId: String = ""

    @Column(name = "product_id", nullable = false)
    @field:JsonProperty("product_id")
    var productId: String = ""
}