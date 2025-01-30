package com.fluxi.order.modifications

import com.fluxi.order.models.OrderModification

interface BaseOrderModification {
    fun makeModification(orderModification: OrderModification): OrderModification
    fun getModificationType(): String
}