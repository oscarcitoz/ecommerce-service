package com.fluxi.websites.requests

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
class UpdateWebsiteRequest {
    var url: String? = null
    var status: String? = null
}