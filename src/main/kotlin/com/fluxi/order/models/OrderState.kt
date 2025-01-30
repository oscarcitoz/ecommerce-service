package com.fluxi.order.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*

@Serdeable
@Entity
@Table(name = "order_states")
@PersistenceContext(name = "order")
class OrderState {
    @Id
    @field:JsonProperty("id")
    var id: String = ""

    companion object {
        const val CREATED = "CREATED"
        const val IN_PROGRESS = "IN_PROGRESS"
        const val FINISHED = "FINISHED"
        const val CANCELED = "CANCELED"
    }
} 