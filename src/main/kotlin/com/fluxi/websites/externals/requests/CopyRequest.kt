package com.fluxi.websites.externals.requests

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Introspected
@Serdeable
class CopyRequest {
    var userId: String = ""
    var prompt: String = ""
    var productName: String = ""
}