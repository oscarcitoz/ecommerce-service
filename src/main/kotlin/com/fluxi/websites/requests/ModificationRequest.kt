package com.fluxi.websites.requests

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
class ModificationRequest {
    var units: Int? = 1
    var confirm: Boolean = true
}