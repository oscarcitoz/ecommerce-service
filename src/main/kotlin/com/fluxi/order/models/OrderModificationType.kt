package com.fluxi.order.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Table

@Serdeable
@Entity
@Table(name = "order_modification_types")
@PersistenceContext(name = "order")
class OrderModificationType {
    @Id
    @field:JsonProperty("id")
    var id: String = ""

    companion object {
        const val ADD_PRODUCT = "add_product"
        const val REJECTED_UPSELL = "rejected_upsell"
    }
}