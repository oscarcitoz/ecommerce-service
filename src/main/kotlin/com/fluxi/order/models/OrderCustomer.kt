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

    @Column(name = "name")
    @field:JsonProperty("name")
    var name: String? = null

    @Column(name = "customer_address")
    @field:JsonProperty("customer_address")
    var customerAddress: String? = null

    @Column(name = "ip_origin")
    @field:JsonProperty("ip_origin")
    var ipOrigin: String? = null

    @Column(name = "prefix_phone")
    @field:JsonProperty("prefix_phone")
    var prefixPhone: String? = null

    @Column(name = "phone")
    @field:JsonProperty("phone")
    var phone: String? = null

    @Column(name = "email")
    @field:JsonProperty("email")
    var email: String? = null

    @Column(name = "created_at", nullable = false)
    @field:JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "customer_id", nullable = false)
    @field:JsonProperty("customer_id")
    var customerId: String = ""

    @Column(name = "identification_number")
    @field:JsonProperty("identification_number")
    var identificationNumber: String? = null

    @Column(name = "identification_type")
    @field:JsonProperty("identification_type")
    var identificationType: String? = null

    @Column(name = "last_name")
    @field:JsonProperty("last_name")
    var lastName: String? = null
} 