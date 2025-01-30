package com.fluxi.order.models

import com.fasterxml.jackson.annotation.JsonProperty
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

    @Column(name = "name", nullable = true)
    @field:JsonProperty("name")
    var name: String? = null

    @Column(name = "last_name", nullable = true)
    @field:JsonProperty("last_name")
    var lastName: String? = null

    @Column(name = "created_at", nullable = false)
    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
} 