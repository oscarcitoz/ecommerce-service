package com.fluxi.order.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fluxi.core.constants.DATE_STRING_FORMAT
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.time.LocalDateTime

@Serdeable
@Entity
@Table(name = "order_store")
@PersistenceContext(name = "order")
class OrderStore {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_store_seq_generator")
    @SequenceGenerator(name = "order_store_seq_generator", sequenceName = "order_store_id_seq", allocationSize = 1)
    @field:JsonProperty("id")
    var id: Long? = null

    @Column(name = "owner_id", nullable = false)
    @field:JsonProperty("owner_id")
    var ownerId: String = ""

    @Column(name = "order_id", nullable = false)
    @field:JsonProperty("order_id")
    var orderId: Long = 0L

    @Column(name = "email", nullable = false)
    @field:JsonProperty("email")
    var email: String = ""

    @JsonFormat(pattern= DATE_STRING_FORMAT)
    @Column(name = "created_at", nullable = false)
    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
} 