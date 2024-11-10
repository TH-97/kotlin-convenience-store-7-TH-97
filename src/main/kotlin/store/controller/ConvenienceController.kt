package store.controller

import store.model.NormalProducts
import store.model.PromotionsProducts
import store.view.OutputView

class ConvenienceController() {
    private lateinit var normalProducts: List<NormalProducts>
    private lateinit var promotionsProducts: List<PromotionsProducts>

    fun saveProducts(
        promotionsProductsList: List<PromotionsProducts>,
        normalProductList: List<NormalProducts>
    ) {
        this.promotionsProducts = promotionsProductsList
        this.normalProducts = normalProductList
    }

    fun openConvenience() {
        OutputView().introductionConvenience()
    }
}