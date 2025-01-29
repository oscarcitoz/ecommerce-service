package com.fluxi.order.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.hypersistence.utils.hibernate.type.json.JsonType
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import org.hibernate.annotations.Type
import java.time.LocalDateTime

@Serdeable
@Entity
@Table(name = "order_modifications")
class OrderModification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_modifications_seq_generator")
    @SequenceGenerator(name = "order_modifications_seq_generator", sequenceName = "order_modifications_id_seq", allocationSize = 1)
    @field:JsonProperty("id")
    var id: Long? = null

    @Column(name = "order_id", nullable = false)
    @field:JsonProperty("order_id")
    var orderId: Long = 0L

    @Column(name = "event_id", nullable = false)
    @field:JsonProperty("event_id")
    var eventId: String = ""

    @Type(JsonType::class)
    @Column(name = "raw", columnDefinition = "jsonb")
    @field:JsonProperty("raw")
    var raw: Map<String, Any>? = null

    @Column(name = "created_at", nullable = false)
    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
} 