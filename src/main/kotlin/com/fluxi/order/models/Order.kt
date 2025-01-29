package com.fluxi.order.models

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*

@Serdeable
@Entity(name = "orders")
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_generator")
    @SequenceGenerator(name = "order_seq_generator", sequenceName = "orders_id_seq", allocationSize = 1)
    var id: Long? = null
}