package com.fluxi.offer.repositories

import com.fluxi.offer.models.DiscountType
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository("offer")
interface DiscountTypeRepository : CrudRepository<DiscountType, String> 