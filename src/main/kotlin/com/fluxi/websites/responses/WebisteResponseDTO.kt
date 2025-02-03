import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fluxi.websites.models.Website
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import java.math.BigDecimal
import java.time.LocalDateTime


@Serdeable
@Introspected
data class WebsiteResponseDTO (
    val id: String,
    val ownerId: String? = null,
    val productId: String? = null,
    val productDescription: String? = null,
    val productWarranties: BigDecimal? = null,
    val price: BigDecimal? = null,
    val name: String? = null,
    val copies: Map<String, Any?>? = null,
    val images: List<String>? = null,
    val url: String? = null,
    val status: String? = null,
    val templateDesign: Map<String, Any>? = null,
    val upsellProductId: String? = null,
    val upsellProductPrice: BigDecimal? = null,
    val upsellProductImage: String? = null,
    val downSellProductId: String? = null,
    val downSellProductPrice: BigDecimal? = null,
    val downSellProductPriceWithDiscount: BigDecimal? = null,
    val downSellProductImage: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val deletedAt: LocalDateTime? = null
)
