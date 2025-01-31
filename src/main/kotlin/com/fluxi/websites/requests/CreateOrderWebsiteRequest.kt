package com.fluxi.websites.requests

import com.fluxi.order.requests.CustomerRequest
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
class CreateOrderWebsiteRequest {
    var customer: CustomerRequest = CustomerRequest()
    var units: Int = 1
}