package com.fluxi.store.models

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*

@Serdeable
@Entity(name = "products")
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_generator")
    @SequenceGenerator(name = "product_seq_generator", sequenceName = "products_id_seq", allocationSize = 1)
    var id: Long? = null
}