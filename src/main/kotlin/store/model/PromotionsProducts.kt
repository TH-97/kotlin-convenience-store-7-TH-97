package store.model

class PromotionsProducts(
    private val name: String,
    private val price: Int,
    private val quantity: Int,
    private val promotion: String?
) {
    fun display(): String {
        return if (quantity > 0) {
            "$name ${price}원 ${quantity}개${if (!promotion.isNullOrEmpty()) " $promotion" else ""}"
        } else {
            "$name ${price}원 재고 없음"
        }
    }
}
