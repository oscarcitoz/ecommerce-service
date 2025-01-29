package com.fluxi.offer.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Serdeable
@Entity
@Table(name = "discount_types")
class DiscountType {
    @Id
    @field:JsonProperty("id")
    var id: String = ""
} 