package com.fluxi.order.calculators

import com.fluxi.order.dtos.DirectorDTO

interface OrderTotalCalculatorInterface {
    fun calculateCreation(directorDTO: DirectorDTO): DirectorDTO
}