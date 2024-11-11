package store.model

class ShoppingBasket(
    private val name: String,
    private val price: Int,
    private val quantity: Int
) {

    fun getName(): String {
        return name
    }

    fun getPrice(): Int {
        return price
    }

    fun getQuantity(): Int {
        return quantity
    }
}