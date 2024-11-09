package store.model

class NormalProducts(
    override val name: String,
    override val price: String,
    override val quantity: String,
    override val promotion: String?
) : Products {
    fun display(): String {
        return "$name ${price}원 ${quantity}개${if (!promotion.isNullOrEmpty()) " $promotion" else ""}"
    }

    override fun toString(): String {
        return "PromotionsProducts(name='$name', price='$price', quantity='$quantity', promotion='$promotion')"
    }

    fun getName(): String {
        return name
    }
}