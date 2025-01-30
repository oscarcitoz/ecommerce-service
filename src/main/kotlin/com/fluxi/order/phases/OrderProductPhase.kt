package com.fluxi.order.phases

import com.fluxi.order.dtos.DirectorDTO
import io.micronaut.core.annotation.Order

@Order(4)
class OrderProductPhase: BasePhase {
    override fun apply(dto: DirectorDTO): DirectorDTO {
        return dto
    }
}