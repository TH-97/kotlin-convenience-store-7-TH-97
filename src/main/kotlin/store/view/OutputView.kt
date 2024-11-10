package store.view

import store.model.NormalProducts
import store.model.PromotionsProducts

class OutputView {
    private lateinit var normalProducts: List<NormalProducts>
    private lateinit var promotionsProducts: List<PromotionsProducts>

    fun introductionConvenience() {
        println("안녕하세요. W편의점입니다.")
        println("현재 보유하고 있는 상품입니다.")
    }
}