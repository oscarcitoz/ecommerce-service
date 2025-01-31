package com.fluxi.websites.requests

import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import io.micronaut.serde.annotation.Serdeable
import java.math.BigDecimal

@Serdeable
@Introspected
class CreateWebsiteRequest {
    var productName: String = ""
    var productDescription: String = ""
    var productWarranty: String = ""
    var productImages: Array<String> = arrayOf()
    var productPrice: BigDecimal = BigDecimal.ZERO
    var productDiscountPrice: Number? = null
    var paymentMethod: PaymentMethod = PaymentMethod()
    var upSell: UpSell = UpSell()
    var downSell: DownSell? = null
    var isFreeShipping: Boolean = false
    var templateDesign: Map<String, Any>? = null
    var userId: String? = null
    var email: String? = null

    fun userIdNotNull(): String {
        return this.userId ?: this.email ?: throw HttpStatusException(HttpStatus.BAD_REQUEST, "NOT USER ID WITHOUT EMAIL")
    }
}

@Serdeable
@Introspected
class UpSell {
    var name: String = ""
    var price: BigDecimal = BigDecimal.ZERO
    var image: String = ""
}

@Serdeable
@Introspected
class DownSell {
    var name: String = ""
    //TODO CALCULATE PRICE_WITH_DISCOUNT
    var price: BigDecimal = BigDecimal.ZERO
    var percentage: Int = 0
    var image: String = ""
}

@Serdeable
@Introspected
class PaymentMethod {
    var isCashOnDelivery: Boolean = false
    var isOnlinePayment: Boolean = false
}
