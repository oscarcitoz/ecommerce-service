package com.fluxi.order.phases.creations

import com.fluxi.order.dtos.DirectorDTO
import com.fluxi.order.models.OrderCustomer
import com.fluxi.order.repositories.OrderCustomerRepository
import io.micronaut.core.annotation.Order
import jakarta.inject.Singleton

@Order(2)
@Singleton
class OrderCustomerPhase(
    private val orderCustomerRepository: OrderCustomerRepository
) : BaseCreationPhase {
    override fun apply(dto: DirectorDTO): DirectorDTO {
        val customer = dto.request.customer

        dto.orderCustomer = this.orderCustomerRepository.save(
            OrderCustomer().apply {
                this.orderId = dto.order.id!!
                this.name = customer.name
                this.email = customer.email
                this.phone = customer.phone
                this.prefixPhone = customer.prefixPhone
                this.address = customer.address
                this.lastName = customer.lastName
                this.ipOrigin = customer.ipOrigin
            }
        )

        return dto
    }
}