package store.controller

import store.model.NormalProducts
import store.model.Promotion
import store.model.PromotionsProducts
import store.utils.Validator
import store.view.InputView
import store.view.OutputView

object ConvenienceController {
    private lateinit var promotionsProducts: MutableList<PromotionsProducts>
    private lateinit var normalProducts: MutableList<NormalProducts>
    private lateinit var promotion: List<Promotion>

    fun ready(
        promotionsProductLists: MutableList<PromotionsProducts>,
        normalProductLists: MutableList<NormalProducts>,
        promotion: List<Promotion>
    ) {
        this.promotionsProducts = promotionsProductLists
        this.normalProducts = normalProductLists
        this.promotion = promotion

        run()
    }

    fun run() {
        openConvenience()
    }

    fun openConvenience() {
        OutputView().introductionConvenience()
        return introductionProducts()
    }

    fun introductionProducts() {
        OutputView().outputProducts(promotionsProducts, normalProducts)
        buyProduct()
    }

    fun buyProduct() {
        try {
            val purchasedProduct = InputView().howToUse()
            Validator().validatePurchasedProduct(purchasedProduct)
            resetInput(purchasedProduct)

        } catch (e: IllegalArgumentException) {
            println(e)
            buyProduct()
        }

    }

    fun checkProduct(productName: String): Boolean {
        for (value in normalProducts) {
            if (value.getName() == productName) return true
        }
        for (value in promotionsProducts) {
            if (value.getName() == productName) return true
        }
        return false
    }

    fun checkProductQuality(productName: String, productQuality: Int): Boolean {
        var sum = 0
        for (value in normalProducts) {
            if (value.getName() == productName) sum += value.getQuantity().toInt()
        }
        for (value in promotionsProducts) {
            if (value.getName() == productName) sum += value.getQuantity().toInt()
        }
        return productQuality <= sum
    }

    fun resetInput(purchasedProduct: String) {
        val items = purchasedProduct.split(",").map { it.trim() }

        items.map { item ->
            val content = item.substring(1, item.length - 1)
            val parts = content.split("-")
            return checkPromotion(parts[0], parts[1].toInt())
        }
    }

    fun checkPromotion(name: String, quantity: Int) {
        val product = promotionsProducts.find { it.getName() == name }
        val buy = promotion.find { it.getName() == product?.getPromotion() }?.getBuy()
        val get = promotion.find { it.getName() == product?.getPromotion() }?.getGet()
        if (promotionsProducts.equals(name)) final()
        product?.getQuantity()?.toInt()?.let {
            if (it > quantity)

                checkQuantity(buy, get, name, quantity)
        }
    }

    fun checkQuantity(buy: String?, get: String?, name: String, quantity: Int) {

    }

    fun final() {
        try {
            val input = InputView().MembershipDiscount().trim()
            Validator().validatefinal(input)
            if (input == "Y") return//멥버십 할인 적용
            if (input == "N") return //멥버십 할인 미적용
        } catch (e: IllegalArgumentException) {
            println(e)
            final()
        }
    }
}