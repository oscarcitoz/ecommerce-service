package com.fluxi.order.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Size

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
    @field:Size(max = 255, message = "name can have a maximum of 255 characters")
    var name: String = ""
    @field:Size(max = 255, message = "address can have a maximum of 255 characters")
    var address: String = ""
    var ipOrigin: String = ""
    @field:Size(max = 10, message = "prefix_phone can have a maximum of 10 characters")
    @field:JsonProperty("prefix_phone")
    var prefixPhone: String = ""
    @field:Size(max = 255, message = "phone can have a maximum of 255 characters")
    var phone: String = ""
    @field:Size(max = 255, message = "email can have a maximum of 255 characters")
    var email: String = ""
    @field:Size(max = 255, message = "last_name can have a maximum of 255 characters")
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