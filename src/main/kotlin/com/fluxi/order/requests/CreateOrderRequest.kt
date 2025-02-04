package com.fluxi.order.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
class CreateOrderRequest {
    var customer: CustomerRequest = CustomerRequest()
    var product: ProductRequest = ProductRequest()
    var store: StoreRequest = StoreRequest()
}

@Serdeable
@Introspected
class CustomerRequest {
    var name: String = ""
    var address: String = ""
    var ipOrigin: String = ""
    @field:JsonProperty("prefix_phone")
    var prefixPhone: String = ""
    var phone: String = ""
    var email: String = ""
    @field:JsonProperty("last_name")
    var lastName: String = ""
}

@Serdeable
@Introspected
class ProductRequest {
    @field:JsonProperty("product_id")
    var productId: String = ""
    var units: Int = 0
}

@Serdeable
@Introspected
class StoreRequest {
    @field:JsonProperty("owner_id")
    var ownerId: String = ""
    var email: String = ""
}