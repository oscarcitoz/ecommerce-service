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
        const val CREATED = "created"
        const val IN_PROGRESS = "in_progress"
        const val FINISHED = "finished"
        const val CANCELED = "canceled"

        fun activeStates() = listOf(CREATED, IN_PROGRESS)
    }
} 