package com.fluxi.websites.requests

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
class CreateWebsiteRequest {
    var productName: String = ""
    var productDescription: String = ""
    var productWarranty: String = ""
    var productImages: Array<String> = arrayOf()
    var productPrice: Number? = null
    var productDiscountPrice: Number? = null
    var paymentMethod: PaymentMethod = PaymentMethod()
    var upSell: UpSell = UpSell()
    var downSell: DownSell = DownSell()
    var isFreeShipping: Boolean = false
    var templateDesign: Map<String, Any?>? = null
    var userId: String? = null
    var email: String? = null
}

@Serdeable
@Introspected
class UpSell {
    var name: String = ""
    var price: Number = 0
    var image: String = ""
}

@Serdeable
@Introspected
class DownSell {
    var name: String = ""
    var price: Number? = null
    var porcentage: Number = 0
    var image: String = ""
}

@Serdeable
@Introspected
class PaymentMethod {
    var isCashOnDelivery: Boolean = false
    var isOnlinePayment: Boolean = false
}
