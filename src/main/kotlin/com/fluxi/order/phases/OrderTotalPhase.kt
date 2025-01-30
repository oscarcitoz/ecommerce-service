package com.fluxi.order.phases

import com.fluxi.order.dtos.DirectorDTO
import io.micronaut.core.annotation.Order

@Order(6)
class OrderTotalPhase: BasePhase {
    override fun apply(dto: DirectorDTO): DirectorDTO {
        return dto
    }
}