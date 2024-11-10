package store.controller

import store.model.NormalProducts
import store.model.PromotionsProducts
import store.view.OutputView

class ConvenienceController() {
    private lateinit var promotionsProducts: List<PromotionsProducts>
    private lateinit var normalProducts: List<NormalProducts>

    fun saveProducts(
        promotionsProductsList: List<PromotionsProducts>,
        normalProductList: List<NormalProducts>
    ) {
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