package store.controller

import store.model.NormalProducts
import store.model.PromotionsProducts
import store.service.InitializationProducts
import store.view.OutputView

class ConvenienceController() {
    private lateinit var promotionsProducts: MutableList<PromotionsProducts>
    private lateinit var normalProducts: MutableList<NormalProducts>

    fun saveProducts(
        promotionsProductsList: MutableList<PromotionsProducts>,
        normalProductList: MutableList<NormalProducts>
    ) {
        InitializationProducts().initializationProducts(promotionsProductsList, normalProductList)
        this.promotionsProducts = promotionsProductsList
        this.normalProducts = normalProductList

        return openConvenience()
    }

    fun openConvenience() {
        OutputView().introductionConvenience()
//        return introductionProducts()
    }

//    fun introductionProducts() {
//        OutputView().outputProducts(promotionsProducts, normalProducts)
//    }
}