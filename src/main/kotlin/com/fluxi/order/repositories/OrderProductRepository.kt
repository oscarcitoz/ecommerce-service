package com.fluxi.order.repositories

import com.fluxi.order.models.OrderProduct
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository("order")
interface OrderProductRepository : CrudRepository<OrderProduct, Long> {
}