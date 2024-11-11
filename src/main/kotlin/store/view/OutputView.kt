package store.view

import store.model.NormalProducts
import store.model.PromotionsProducts

class OutputView {
    private lateinit var normalProducts: List<NormalProducts>
    private lateinit var promotionsProducts: List<PromotionsProducts>

    fun introductionConvenience() {
        println("안녕하세요. W편의점입니다.")
        println("현재 보유하고 있는 상품입니다.\n")
    }

    fun outputProducts(
        promotionsProducts: List<PromotionsProducts>,
        normalProducts: List<NormalProducts>
    ) {
        this.promotionsProducts = promotionsProducts
        this.normalProducts = normalProducts
        return oneLineOneTime()
    }

    fun oneLineOneTime() {
        for (value in normalProducts) {
            normalProductsJudgment(value)
        }
    }

    fun normalProductsJudgment(value: NormalProducts) {
        if (value.getQuantity().toInt() > 0) {
            val find = promotionsProducts.filter { it.getName() == value.getName() }
            if (find.isNotEmpty())
                println(
                    "- ${find.first().getName()} ${find.first().getPrice()}원 " +
                            "${find.first().getQuantity()}개 ${find.first().getPromotion()}"
                )
            println("- ${value.getName()} ${value.getPrice()}원 ${value.getQuantity()}개")
        }
        if (value.getQuantity().toInt() <= 0) notHaveNormalProducts(value)

    }

    fun notHaveNormalProducts(value: NormalProducts) {
        val find = promotionsProducts.filter { it.getName() == value.getName() }
        if (find.isNotEmpty())
            println(
                "- ${find.first().getName()} ${find.first().getPrice()}원 " +
                        "${find.first().getQuantity()}개 ${find.first().getPromotion()}"
            )
        println("- ${value.getName()} ${value.getPrice()}원 재고 없음")
    }
}