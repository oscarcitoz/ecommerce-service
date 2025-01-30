package com.fluxi.order.repositories

import com.fluxi.order.models.OrderModification
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository("order")
interface OrderModificationRepository : CrudRepository<OrderModification, Long> {
}