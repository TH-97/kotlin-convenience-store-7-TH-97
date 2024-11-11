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
        var buy = 0
        var get = 0
        val product = promotionsProducts.find { it.getName() == name }
        val findBuy = promotion.find { it.getName() == product?.getPromotion() }?.getBuy()
        if (!findBuy.isNullOrEmpty()) buy = findBuy.toInt()
        val findGet = promotion.find { it.getName() == product?.getPromotion() }?.getGet()
        if (!findGet.isNullOrEmpty()) get = findGet.toInt()
        if (promotionsProducts.equals(name)) final() //프로모션 제품이 존재하지 않아서 바로 멥버십 할인으로 안내
        product?.getQuantity()?.toInt()?.let {
            if (it >= quantity) {
                checkQuantity(buy, get, name, quantity) //많거나 같다
            } else {
                notEnoughPromotionProduct(name, quantity)
            }
        }
    }

    fun notEnoughPromotionProduct(name: String, quantity: Int) {
        try {
            val input = InputView().buyAll(name, quantity)
            Validator().validateYorN(input)
            if (input == "Y") final() // 그냥 구매를 하겠다
            if (input == "N") buyProduct()// 구매 하지 않겠다
        } catch (e: IllegalArgumentException) {
            println(e)
            oneMore(name)
        }
    }

    fun checkQuantity(buy: Int, get: Int, name: String, quantity: Int) {
        if (quantity % buy + get == buy) {
            oneMore(name)//하나를 더 받을 수 있는 상황
        } else {
            final() // 멥버십 할인 안내
        }
    }

    fun oneMore(name: String) {
        try {
            val input = InputView().oneMorePrint(name)
            Validator().validateYorN(input)
            if (input == "Y") return //프로모션 증정을 받겠다
            if (input == "N") final()//프로모션 증정을 받지 않겠다
        } catch (e: IllegalArgumentException) {
            println(e)
            oneMore(name)
        }
    }

    fun final() {
        try {
            val input = InputView().membershipDiscount().trim()
            Validator().validateYorN(input)
            if (input == "Y") return//멥버십 할인 적용
            if (input == "N") return //멥버십 할인 미적용
        } catch (e: IllegalArgumentException) {
            println(e)
            final()
        }
    }
}