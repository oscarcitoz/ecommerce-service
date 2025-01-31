package com.fluxi.websites.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.hypersistence.utils.hibernate.type.json.JsonType
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import org.hibernate.annotations.Type
import java.time.LocalDateTime

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

    @Column(name = "name")
    @field:JsonProperty("name")
    var name: String = ""

    @Column(name = "type")
    @field:JsonProperty("type")
    var type: WebsiteType = WebsiteType.LANDING

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
    var status: WebsiteStatus = WebsiteStatus.BUILDING

    @Type(JsonType::class)
    @Column(name = "template_design", columnDefinition = "jsonb")
    @field:JsonProperty("template_design")
    var template_design: Map<String, Any>? = null

    @Column(name = "upsell_id")
    @field:JsonProperty("upsell_id")
    var upsell_id: String = ""

    @Column(name = "downsell_id")
    @field:JsonProperty("downsell_id")
    var downsell_id: String = ""

    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @field:JsonProperty("updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()
}

enum class WebsiteType {
    LANDING,
    UPSELL,
    DOWNSELL
}

enum class WebsiteStatus {
    BUILDING,
    PUBLISH,
    PRIVATE,
    HIDDEN,
}
