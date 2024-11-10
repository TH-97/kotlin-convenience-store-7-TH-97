package store.model

class NormalProducts(
    private val name: String,
    private val price: String,
    private val quantity: String,
    private val promotion: String?
) {
    fun display(): String {
        return "$name ${price}원 ${quantity}개${if (!promotion.isNullOrEmpty()) " $promotion" else ""}"
    }

    override fun toString(): String {
        return "PromotionsProducts(name='$name', price='$price', quantity='$quantity', promotion='$promotion')"
    }

    fun getName(): String {
        return name
    }

    fun getPrice(): String {
        return String.format("%,d", price.toInt())
    }

    fun getQuantity(): String {
        return quantity
    }

    fun getPromotion(): String? {
        return promotion
    }
}