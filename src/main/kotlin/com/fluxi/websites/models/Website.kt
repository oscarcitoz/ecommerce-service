package com.fluxi.websites.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.hypersistence.utils.hibernate.type.json.JsonType
import io.micronaut.data.annotation.Where
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import org.hibernate.annotations.Type
import java.math.BigDecimal
import java.time.LocalDateTime

@Serdeable
@Entity
@Table(name = "website")
@PersistenceContext(name = "website")
@Where(value = "deletedAt IS NULL")
class Website {
    @Id
    @field:JsonProperty("id")
    var id: String = ""

    @Column(name = "owner_id")
    @field:JsonProperty("owner_id")
    var ownerId: String = ""

    @Column(name = "product_id")
    @field:JsonProperty("product_id")
    var productId: String = ""

    @Column(name = "product_description")
    @field:JsonProperty("product_description")
    var productDescription: String = ""

    @Column(name = "product_warranties")
    @field:JsonProperty("product_warranties")
    var productWarranties: BigDecimal = BigDecimal.ZERO

    @Column(name = "price", nullable = false)
    @field:JsonProperty("price")
    var price: BigDecimal = BigDecimal.ZERO

    @Column(name = "name")
    @field:JsonProperty("name")
    var name: String = ""

    @field:Column(name = "copies")
    @field:JsonProperty("copies")
    @field:Type(JsonType::class)
    var copies: Map<String, Any?>? = null

    @field:Column(name = "images")
    @field:JsonProperty("images")
    @field:Type(JsonType::class)
    var images: List<String> = listOf()

    @field:JsonProperty("url")
    @Column(name = "url")
    var url: String = ""

    @field:JsonProperty("status")
    @Column(name = "status")
    var status: String = WebsiteStatus.BUILDING

    @Column(name = "is_free_shipping")
    @field:JsonProperty("is_free_shipping")
    var isFreeShipping: Boolean? = false

    @field:Column(name = "template_design", columnDefinition = "jsonb")
    @field:JsonProperty("template_design")
    @field:Type(JsonType::class)
    var templateDesign: Map<String, Any>? = null

    @Column(name = "upsell_product_id")
    @field:JsonProperty("upsell_product_id")
    var upsellProductId: String? = null

    @Column(name = "upsell_product_price")
    @field:JsonProperty("upsell_product_price")
    var upsellProductPrice: BigDecimal? = null

    @Column(name = "upsell_product_image")
    @field:JsonProperty("upsell_product_image")
    var upsellProductImage: String? = null

    @Column(name = "down_sell_product_id")
    @field:JsonProperty("down_sell_product_id")
    var downSellProductId: String? = null

    @Column(name = "down_sell_product_price")
    @field:JsonProperty("down_sell_product_price")
    var downSellProductPrice: BigDecimal? = null

    @Column(name = "down_sell_product_price_with_discount")
    @field:JsonProperty("down_sell_product_price_with_discount")
    var downSellProductPriceWithDiscount: BigDecimal? = null

    @Column(name = "down_sell_product_image")
    @field:JsonProperty("down_sell_product_image")
    var downSellProductImage: String? = null

    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @field:JsonProperty("updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "deleted_at")
    @field:JsonProperty("deleted_at")
    var deletedAt: LocalDateTime? = null
}

class WebsiteStatus {
    companion object {
        val BUILDING = "BUILDING"
        val PUBLISH = "PUBLISH"
        val PRIVATE = "PRIVATE"
        val HIDDEN = "HIDDEN"
    }
}

class WebsiteType {
    companion object {
        val UPSELL = "upsell"
        val DOWNSELL = "downsell"
    }
}
