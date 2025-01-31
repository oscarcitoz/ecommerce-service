package com.fluxi.order.phases

import com.fluxi.order.dtos.DirectorDTO

interface BasePhase {
    fun apply(dto: DirectorDTO): DirectorDTO
}