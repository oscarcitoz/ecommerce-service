package com.fluxi.order.requests

import com.fasterxml.jackson.annotation.JsonProperty

class CreateOrderRequest {
    var customer: CustomerRequest = CustomerRequest()
    var product: ProductRequest = ProductRequest()
}

class CustomerRequest {
    var name: String = ""
    var address: String = ""
    var ipOrigin: String = ""
    @field:JsonProperty("prefix_phone")
    var prefixPhone: String = ""
    var phone: String = ""
    var email: String = ""
    var lastName: String = ""
}

class ProductRequest {
    @field:JsonProperty("product_id")
    var productId: String = ""
    var units: Int = 0
}