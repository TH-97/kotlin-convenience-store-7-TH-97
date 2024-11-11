package store.controller

import store.model.NormalProducts
import store.model.PresentationProducts
import store.model.Promotion
import store.model.PromotionsProducts
import store.model.ShoppingBasket
import store.utils.Validator
import store.view.InputView
import store.view.OutputView

object ConvenienceController {
    private lateinit var promotionsProducts: MutableList<PromotionsProducts>
    private lateinit var normalProducts: MutableList<NormalProducts>
    private lateinit var promotion: List<Promotion>
    private var shoppingBasket: MutableList<ShoppingBasket> = mutableListOf()
    private var presentationProduct: MutableList<PresentationProducts> = mutableListOf() //증정 list
    private var purchaseAmount = 0 //현재 구입 예정 금액
    private var discountedAmount = 0 //프로모션 할인 예정 금액
    private var membership = 0 // 멥버십 할인 예정 금액

    fun ready(
        promotionsProductLists: MutableList<PromotionsProducts>,
        normalProductLists: MutableList<NormalProducts>,
        promotion: List<Promotion>
    ) {
        this.promotionsProducts = promotionsProductLists
        this.normalProducts = normalProductLists
        this.promotion = promotion
        for (value in promotionsProducts) {
            promotion.find { it.getName() == value.getName() }

        }
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
            val price = findPrice(parts[0])
            shoppingBasket.add(ShoppingBasket(parts[0], price * parts[1].toInt(), parts[1].toInt()))
            purchaseAmount += price * parts[1].toInt() // 구입 예정 금액 더하기
//            return checkPromotion(parts[0], parts[1].toInt())
        }
        if (tryCatch()) final()
    }

    fun tryCatch(): Boolean {
        return try {
            for (value in shoppingBasket) {
                checkPromotion(value.getName(), value.getQuantity().toInt())
            }
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun findPrice(name: String): Int {
        for (value in normalProducts) {
            if (value.getName() == name) return value.getPriceInt()
        }
        return 0
    }

    fun checkPromotion(name: String, quantity: Int) {
        var buy = 0
        var get = 0
        val product = promotionsProducts.find { it.getName() == name }
        val findBuy = promotion.find { it.getName() == product?.getPromotion() }?.getBuy()
        if (!findBuy.isNullOrEmpty()) buy = findBuy.toInt()
        val findGet = promotion.find { it.getName() == product?.getPromotion() }?.getGet()
        if (!findGet.isNullOrEmpty()) get = findGet.toInt()
        if (promotionsProducts.equals(name)) return //프로모션 제품이 존재하지 않아서 바로 멥버십 할인으로 안내
        product?.getQuantity()?.toInt()?.let {
            if (it >= quantity) {
                checkQuantity(buy, get, name, quantity) //프로모션 제품이 충분하다
            } else {
                notEnoughPromotionProduct(name, quantity) //프로모션 제품이 충분하지 않다
            }
        }
    }

    fun notEnoughPromotionProduct(name: String, quantity: Int) {
        try {
            val input = InputView().buyAll(name, quantity)
            Validator().validateYorN(input)
            if (input == "Y") return // 그냥 구매를 하겠다
            if (input == "N") {
                purchaseAmount = 0
                discountedAmount = 0
                membership = 0
                shoppingBasket = mutableListOf()
                buyProduct()// 구매 하지 않겠다
            }
        } catch (e: IllegalArgumentException) {
            println(e)
            notEnoughPromotionProduct(name, quantity)
        }
    }

    fun checkQuantity(buy: Int, get: Int, name: String, quantity: Int) {
        if (quantity % (buy + get) == buy) {
            oneMore(name)//하나를 더 받을 수 있는 상황
        }
    }

    fun oneMore(name: String) {
        try {
            val input = InputView().oneMorePrint(name)
            Validator().validateYorN(input)

            if (input == "Y") { // 프로모션 증정을 받겠다
                val basketItem = shoppingBasket.find { it.getName() == name }
                val normalPrice = normalProducts.find { it.getName() == name }?.getPriceInt()
                if (basketItem != null && normalPrice != null) {
                    basketItem.setPrice(basketItem.getPrice() + normalPrice)
                    basketItem.setQuantity(basketItem.getQuantity() + 1)
                    purchaseAmount += normalPrice
                }
            }
            if (input == "N") return//프로모션 증정을 받지 않겠다
        } catch (e: IllegalArgumentException) {
            println(e)
            oneMore(name)
        }
    }

    fun final() {
        try {
            val input = InputView().membershipDiscount().trim()
            Validator().validateYorN(input)
            if (input == "Y") { //멥버십 할인 적용
//                for (int)
            }
            if (input == "N") notMembershipReceipt() //멥버십 할인 미적용
        } catch (e: IllegalArgumentException) {
            println(e)
            final()
        }
    }

    fun notMembershipReceipt() {
        for (value in shoppingBasket) {
            val products = promotionsProducts.find { it.getName() == value.getName() }
            if (products != null) {
                var buy = 0
                var get = 0
                val findBuy = promotion.find { it.getName() == products?.getPromotion() }?.getBuy()
                if (!findBuy.isNullOrEmpty()) buy = findBuy.toInt()
                val findGet = promotion.find { it.getName() == products?.getPromotion() }?.getGet()
                if (!findGet.isNullOrEmpty()) get = findGet.toInt()
                var productName = products.getName()
                var bonusQuantity = value.getQuantity() / (buy + get)

                var discountedAmountProduct = products.getPriceInt() * bonusQuantity
                discountedAmount += products.getPriceInt() * bonusQuantity
                presentationProduct.add(
                    PresentationProducts(
                        productName,
                        bonusQuantity,
                        discountedAmountProduct
                    )
                )
            }


        }

        OutputView().outPutNotMembershipReceipt(
            shoppingBasket,
            presentationProduct,
            purchaseAmount,
            discountedAmount,
            membership
        )
        shoppingBasket = mutableListOf()
        presentationProduct = mutableListOf() //증정 list
        purchaseAmount = 0 //현재 구입 예정 금액
        discountedAmount = 0 //프로모션 할인 예정 금액
        membership = 0 // 멥버십 할인 예정 금액

    }

}