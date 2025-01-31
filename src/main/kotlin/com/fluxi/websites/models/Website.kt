package com.fluxi.websites.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.hypersistence.utils.hibernate.type.json.JsonType
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import org.hibernate.annotations.Type
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.jvm.Transient

@Serdeable
@Entity
@Table(name = "website")
@PersistenceContext(name = "website")
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

    @Column(name = "price", nullable = false)
    @field:JsonProperty("price")
    var price: BigDecimal = BigDecimal.ZERO

    @Column(name = "name")
    @field:JsonProperty("name")
    var name: String = ""

    @Column(name = "type")
    @field:JsonProperty("type")
    var type: String = WebsiteType.LANDING

    @Type(JsonType::class)
    @Column(name = "copies")
    @field:JsonProperty("copies")
    var copies: Map<String, Any>? = null

    @Type(JsonType::class)
    @Column(name = "images")
    @field:JsonProperty("images")
    var images: List<String> = listOf()

    @field:JsonProperty("url")
    @Column(name = "url")
    var url: String = ""

    @field:JsonProperty("status")
    @Column(name = "status")
    var status: String = WebsiteStatus.BUILDING

    @Type(JsonType::class)
    @Column(name = "template_design", columnDefinition = "jsonb")
    @field:JsonProperty("template_design")
    var templateDesign: Map<String, Any>? = null

    @Column(name = "upsell_id")
    @field:JsonProperty("upsell_id")
    var upsellId: String? = null

    @Transient
    @field:JsonProperty("upsell_website")
    var upsellWebsite: Website? = null

    @Column(name = "downsell_id")
    @field:JsonProperty("downsell_id")
    var downsellId: String? = null

    @Transient
    @field:JsonProperty("downsell_website")
    var downsellWebsite: Website? = null

    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @field:JsonProperty("updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()
}

class WebsiteType {
    companion object {
        val LANDING = "LANDING"
        val UPSELL = "UPSELL"
        val DOWNSELL = "DOWNSELL"
    }
}

class WebsiteStatus {
    companion object {
        val BUILDING = "BUILDING"
        val PUBLISH = "PUBLISH"
        val PRIVATE = "PRIVATE"
        val HIDDEN = "HIDDEN"
    }
}
