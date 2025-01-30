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

    @field:JsonProperty("owner_id")
    var ownerId: String = ""

    @field:JsonProperty("product_id")
    var productId: String = ""

    @field:JsonProperty("name")
    var name: String = ""

    @field:JsonProperty("type")
    var type: WebsiteType = WebsiteType.LANDING

    @Type(JsonType::class)
    @field:JsonProperty("copys")
    var copys: Map<String, Any>? = null

    @field:JsonProperty("url")
    var url: String = ""

    @field:JsonProperty("status")
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
