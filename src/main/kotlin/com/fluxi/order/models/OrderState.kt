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
    @field:JsonProperty("name")
    var name: String = ""
} 