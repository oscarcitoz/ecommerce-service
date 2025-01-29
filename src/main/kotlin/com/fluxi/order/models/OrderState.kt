package com.fluxi.order.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*

@Serdeable
@Entity
@Table(name = "order_states")
class OrderState {
    @Id
    @field:JsonProperty("name")
    var name: String = ""
    
    @Column(name = "description", nullable = false)
    @field:JsonProperty("description")
    var description: String = ""
} 