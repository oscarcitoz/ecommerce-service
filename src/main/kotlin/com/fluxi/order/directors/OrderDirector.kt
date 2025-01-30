package com.fluxi.order.directors

import com.fluxi.order.dtos.DirectorDTO
import com.fluxi.order.models.Order
import com.fluxi.order.phases.BasePhase
import com.fluxi.order.phases.creations.BaseCreationPhase
import com.fluxi.order.phases.loaders.BaseLoadPhase
import com.fluxi.order.requests.CreateOrderRequest
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton

@Singleton
open class OrderDirector(
    private val creationPhases: List<BaseCreationPhase>,
    private val loaderPhases: List<BaseLoadPhase>
) : OrderDirectorInterface {

    @Transactional("order")
    override fun make(orderRequest: CreateOrderRequest): Order {
        val directorDTO = this.initDTO(orderRequest)
        this.run(directorDTO, this.loaderPhases)
        this.run(directorDTO, this.creationPhases)

        return directorDTO.order
    }

    private fun initDTO(orderRequest: CreateOrderRequest): DirectorDTO {
        return DirectorDTO().apply {
            this.request = orderRequest
        }
    }

    private fun run(dto: DirectorDTO, phases: List<BasePhase>) {
        phases.forEach {
            it.apply(dto)
        }
    }
}