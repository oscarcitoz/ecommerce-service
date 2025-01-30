package com.fluxi.order.phases.creations

import com.fluxi.order.calculators.OrderTotalCalculatorInterface
import com.fluxi.order.dtos.DirectorDTO
import com.fluxi.order.repositories.OrderProductRepository
import com.fluxi.order.repositories.OrderRepository
import io.micronaut.core.annotation.Order
import jakarta.inject.Singleton

@Order(5)
@Singleton
class OrderTotalPhase(
    private val orderTotalCalculatorInterface: OrderTotalCalculatorInterface,
    private val orderRepository: OrderRepository,
    private val orderProductRepository: OrderProductRepository
) : BaseCreationPhase {
    override fun apply(dto: DirectorDTO): DirectorDTO {
        this.orderTotalCalculatorInterface.calculateCreation(dto)

        this.orderRepository.update(dto.order)
        this.orderProductRepository.update(dto.orderProduct)

        return dto
    }
}