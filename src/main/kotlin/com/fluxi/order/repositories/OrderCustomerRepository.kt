package com.fluxi.order.repositories

import com.fluxi.order.models.OrderCustomer
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository("order")
interface OrderCustomerRepository : CrudRepository<OrderCustomer, Long> {
}