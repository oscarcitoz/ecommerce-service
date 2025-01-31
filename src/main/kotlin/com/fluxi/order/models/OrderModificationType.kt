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
        const val DECLINED_UP_SELL = "declined_up_sell"
        const val DECLINED_DOWN_SELL = "declined_down_sell"
        const val CONFIRM_UP_SELL = "confirm_up_sell"
        const val CONFIRM_DOWN_SELL = "confirm_down_sell"
        const val CHANGE_STATE = "change_state"
    }
}