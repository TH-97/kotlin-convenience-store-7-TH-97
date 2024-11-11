package store.model

class PresentationProducts(
    private val name: String,
    private var price: Int,
    private var quantity: Int
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

    fun setPrice(newPrice: Int) {
        this.price = newPrice
    }

    fun setQuantity(newQuantity: Int) {
        this.quantity = newQuantity
    }
}