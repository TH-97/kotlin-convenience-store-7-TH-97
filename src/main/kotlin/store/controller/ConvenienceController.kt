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
}