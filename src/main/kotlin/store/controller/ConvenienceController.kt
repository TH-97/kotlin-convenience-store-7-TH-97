package store.controller

import store.model.NormalProducts
import store.model.Promotion
import store.model.PromotionsProducts
import store.service.InitializationProducts
import store.view.InputView
import store.view.OutputView

class ConvenienceController() {
    private lateinit var promotionsProducts: MutableList<PromotionsProducts>
    private lateinit var normalProducts: MutableList<NormalProducts>
    private lateinit var promotion: List<Promotion>
    fun saveProductsAndPromotion(
        promotionsProductsList: MutableList<PromotionsProducts>,
        normalProductList: MutableList<NormalProducts>,
        promotion: List<Promotion>
    ) {
        val (promotionsProductsList, normalProductList) =
            InitializationProducts().initializationProducts(
                promotionsProductsList,
                normalProductList
            )
        this.promotionsProducts = promotionsProductsList
        this.normalProducts = normalProductList
        this.promotion = promotion
        return run()
    }

    fun run() {
        openConvenience()
        buyProduct()
    }

    fun openConvenience() {
        OutputView().introductionConvenience()
        return introductionProducts()
    }

    fun introductionProducts() {
        OutputView().outputProducts(promotionsProducts, normalProducts)
    }

    fun buyProduct() {
        InputView().howToUse()
    }
}