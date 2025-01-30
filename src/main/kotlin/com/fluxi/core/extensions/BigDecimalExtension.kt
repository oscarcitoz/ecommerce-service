package com.fluxi.core.extensions

import java.math.BigDecimal
import java.math.RoundingMode

object BigDecimalExtension {
    fun BigDecimal.setDefaultScale(): BigDecimal {
        return this.setScale(2, RoundingMode.HALF_UP)
    }
}