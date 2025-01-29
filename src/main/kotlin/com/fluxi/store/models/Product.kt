package com.fluxi.store.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import org.hibernate.annotations.Type
import java.math.BigDecimal
import java.time.LocalDateTime

@PersistenceContext(name = "store")
@Serdeable
@Entity
@Table(name = "products")
class Product {
    @Id
    @field:JsonProperty("id")
    var id: String = ""

    @Column(name = "owner_id", nullable = false)
    @field:JsonProperty("owner_id")
    var ownerId: String = ""

    @Column(name = "name", nullable = false)
    @field:JsonProperty("name")
    var name: String = ""

    @Column(name = "price", nullable = false)
    @field:JsonProperty("price")
    var price: BigDecimal = BigDecimal.ZERO

    @Column(name = "available", nullable = false)
    @field:JsonProperty("available")
    var available: Boolean = false

    @Column(name = "created_at", nullable = false)
    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at", nullable = false)
    @field:JsonProperty("updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "deleted_at")
    @field:JsonProperty("deleted_at")
    var deletedAt: LocalDateTime? = null

    @Type(value = JsonBinaryType::class)
    @Column(name = "images", nullable = false, columnDefinition = "jsonb")
    @field:JsonProperty("images")
    var images: List<String> = listOf()

    @Column(name = "description")
    @field:JsonProperty("description")
    var description: String? = null

    @Column(name = "category")
    @field:JsonProperty("category")
    var category: String? = null
}