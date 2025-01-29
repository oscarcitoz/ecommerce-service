package com.fluxi.offer.models

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*

@Serdeable
@Entity(name = "offers")
class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offer_seq_generator")
    @SequenceGenerator(name = "offer_seq_generator", sequenceName = "offers_id_seq", allocationSize = 1)
    var id: Long? = null
}