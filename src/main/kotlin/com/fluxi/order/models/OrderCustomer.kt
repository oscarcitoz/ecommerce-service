package com.fluxi.order.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import java.time.LocalDateTime

@Serdeable
@Entity
@Table(name = "order_customer")
class OrderCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_customer_seq_generator")
    @SequenceGenerator(name = "order_customer_seq_generator", sequenceName = "order_customer_id_seq", allocationSize = 1)
    @field:JsonProperty("id")
    var id: Long? = null

    @Column(name = "order_id", nullable = false)
    @field:JsonProperty("order_id")
    var orderId: Long = 0L

    @Column(name = "name", nullable = false)
    @field:JsonProperty("name")
    var name: String = ""

    @Column(name = "address", nullable = false)
    @field:JsonProperty("address")
    var address: String = ""

    @Column(name = "ip_origin")
    @field:JsonProperty("ip_origin")
    var ipOrigin: String? = null

    @Column(name = "prefix_phone", nullable = false)
    @field:JsonProperty("prefix_phone")
    var prefixPhone: String = "|"

    @Column(name = "phone", nullable = false)
    @field:JsonProperty("phone")
    var phone: String = ""

    @Column(name = "email", nullable = false)
    @field:JsonProperty("email")
    var email: String = ""

    @Column(name = "created_at", nullable = false)
    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "last_name", nullable = false)
    @field:JsonProperty("last_name")
    var lastName: String = ""
} 