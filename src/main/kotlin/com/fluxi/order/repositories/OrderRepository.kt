package com.fluxi.order.repositories

import com.fluxi.order.models.Order
import com.fluxi.order.responses.OrderSummary
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.Optional

@Repository("order")
interface OrderRepository : CrudRepository<Order, Long> {
    fun findByHashId(hashId: String): Optional<Order>

    @Query(
        value = """
            SELECT DISTINCT ON (o.id) o.id, o.hash_id as hashId, o.total_value as totalValue,
            o.created_at as createdAt, o.total_value_with_discount as totalValueWithDiscount,
            oc.name as customerName, oc.last_name as customerLastName, oc.prefix_phone as customerPrefixPhone,
            oc.email as customerEmail, oc.address as customerAddress,
            SUM(op.units) OVER (PARTITION BY o.id) AS totalUnits
            FROM orders o 
            JOIN order_store os ON o.id = os.order_id
            JOIN order_customer oc ON o.id = oc.order_id
            JOIN order_product op ON o.id = op.order_id
            WHERE os.owner_id = :ownerId
        """,
        nativeQuery = true
    )
    fun findByOwnerId(ownerId: String): List<OrderSummary>

}