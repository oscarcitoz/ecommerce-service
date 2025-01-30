package com.fluxi.websites.requests

import com.fluxi.order.requests.StoreRequest
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
class CreateWebsiteRequest {
    var product: ProductRequest = ProductRequest()
    var paymentMethod: PaymentMethod = PaymentMethod()
    var upSell: UpSell = UpSell()
    var downSell: DownSell = DownSell()
    var isFreeShipping: Boolean = false
    var templateDesign: Map<String, Any?>? = null
    var userId: String? = null
    var email: String? = null
}

class ProductRequest {
    var name: String = ""
    var description: String = ""
    var warranty: String = ""
    var images: Array<String> = arrayOf()
    var Price: Number? = null
    var discountPrice: Number? = null
}

class UpSell {
    var name: String = ""
    var price: Number = 0
    var image: String = ""
}

class DownSell {
    var name: String = ""
    var price: Number? = null
    var porcentage: Number = 0
    var image: String = ""
}

class PaymentMethod {
    var isCashOnDelivery: Boolean = false
    var isOnlinePayment: Boolean = false
}
