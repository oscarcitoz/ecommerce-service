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

    @field:JsonProperty("url")
    var url: String = ""

    @Type(JsonType::class)
    @Column(name = "configuration", columnDefinition = "jsonb")
    @field:JsonProperty("configuration")
    var configuration: Map<String, Any>? = null

    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @field:JsonProperty("updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()
}

enum class WebsiteType {
    LANDING,
    UPELL,
    DOWNELL
}
