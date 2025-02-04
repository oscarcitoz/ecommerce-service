package com.fluxi.order.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fluxi.core.constants.DATE_STRING_FORMAT
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@PersistenceContext(name = "order")
@Serdeable
@Entity
@Table(name = "orders")
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_generator")
    @SequenceGenerator(name = "order_seq_generator", sequenceName = "orders_id_seq", allocationSize = 1)
    @field:JsonProperty("id")
    var id: Long? = null

    @Column(name = "total_value", nullable = false)
    @field:JsonProperty("total_value")
    var totalValue: BigDecimal = BigDecimal.ZERO

    @Column(name = "notes")
    @field:JsonProperty("notes")
    var notes: String? = null

    @Column(name = "hash_id")
    @field:JsonProperty("hash_id")
    var hashId: String = ""

    @JsonFormat(pattern= DATE_STRING_FORMAT)
    @Column(name = "created_at", nullable = false)
    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @JsonFormat(pattern= DATE_STRING_FORMAT)
    @Column(name = "updated_at", nullable = false)
    @field:JsonProperty("updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "state", nullable = false)
    @field:JsonProperty("state")
    var state: String = ""

    @Column(name = "total_value_with_discount", nullable = false)
    @field:JsonProperty("total_value_with_discount")
    var totalValueWithDiscount: BigDecimal = BigDecimal.ZERO

    fun activeOrder(): Boolean {
        return OrderState.activeStates().contains(this.state)
    }
}