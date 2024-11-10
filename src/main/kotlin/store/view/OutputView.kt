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

//    fun outputProducts(
//        promotionsProducts: List<PromotionsProducts>,
//        normalProducts: List<NormalProducts>
//    ) {
//        this.promotionsProducts = promotionsProducts
//        this.normalProducts = normalProducts
//        return oneLineOneTime()
//    }

//    fun oneLineOneTime() {
//        for ((index, value) in promotionsProducts.withIndex()) {
//            productJudgment(index, value)
//        }
//    }

//    fun productJudgment(index: Int, value: PromotionsProducts) {
//        if (value.getQuantity().toInt() > 0)
//            println("- ${value.getName()} ${value.getPrice()}원 ${value.getQuantity()}개 ${value.getPromotion()}")
//        if (value.getName() == normalProducts[index].getName()) {
//            println(
//                "- ${normalProducts[index].getName()} " +
//                        "${normalProducts[index].getPrice()}원 " +
//                        "${normalProducts[index].getQuantity()}개 " +
//                        "${normalProducts[index].getPromotion()}"
//            )
//        }
//    }

}