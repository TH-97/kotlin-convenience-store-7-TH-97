package store.model

import kotlin.to

class Proucts(val name: String, val price: Int, val quantity: Int, val promotion: String?) {
    fun getProductInfo(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "price" to price,
            "quantity" to quantity,
            "promotion" to promotion
        )
    }
}
