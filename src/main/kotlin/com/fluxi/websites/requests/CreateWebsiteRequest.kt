package com.fluxi.websites.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Size
import java.math.BigDecimal

@Serdeable
@Introspected
class CreateWebsiteRequest {
    @field:Size(max = 255, message = "product name can have a maximum of 255 characters")
    var productName: String = ""

    @field:Size(max = 500, message = "product description can have a maximum of 500 characters")
    var productDescription: String = ""
    var productWarranty: BigDecimal? = null
    var productImages: List<String> = listOf()
    var productPrice: BigDecimal = BigDecimal.ZERO
    var paymentMethod: PaymentMethod? = null
    var upSell: UpSell? = null
    var downSell: DownSell? = null
    var isFreeShipping: Boolean? = null
    var templateDesign: Map<String, Any>? = null
    var userId: String? = null
    var email: String? = null

    fun userIdNotNull(): String {
        return this.userId ?: this.email ?: throw HttpStatusException(
            HttpStatus.BAD_REQUEST,
            "NOT USER ID WITHOUT EMAIL"
        )
    }
}

@Serdeable
@Introspected
class UpSell {
    var name: String = ""
    var price: BigDecimal = BigDecimal.ZERO
    var image: String? = null
    @field:JsonProperty("url_image")
    var urlImage: String? = null
}

@Serdeable
@Introspected
class DownSell {
    var name: String = ""
    var price: BigDecimal = BigDecimal.ZERO
    var percentage: Int = 0
    var image: String? = null
    @field:JsonProperty("url_image")
    var urlImage: String? = null
}

@Serdeable
@Introspected
class PaymentMethod {
    var isCashOnDelivery: Boolean = false
    var isOnlinePayment: Boolean = false
}
