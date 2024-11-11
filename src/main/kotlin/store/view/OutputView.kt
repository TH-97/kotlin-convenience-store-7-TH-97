package store.view

import store.model.NormalProducts
import store.model.PresentationProducts
import store.model.PromotionsProducts
import store.model.ShoppingBasket

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

    fun outPutNotMembershipReceipt(
        shoppingBasket: MutableList<ShoppingBasket>,
        presentationProduct: MutableList<PresentationProducts>,
        purchaseAmount: Int,
        discountedAmount: Int,
        membership1: Int
    ) {
        println("==============W 편의점================")
        println("상품명\t\t수량\t금액")
        for (value in shoppingBasket) {
            println("${value.getName()}\t\t${value.getQuantity()}\t${value.getPrice()}")
        }
        println("=============증\t정===============")
        println("상품명\t\t수량\t금액")
        for (value in presentationProduct) {
            println("${value.getName()}\t\t${value.getPrice()}\t")
        }
        println("====================================")
        println("총구매액\t\t\t${String.format("%,d", purchaseAmount)}")
        println("행사할인\t\t\t-${String.format("%,d", discountedAmount)}")
        println("멤버십할인\t\t\t-0")
        println("내실돈\t\t\t ${String.format("%,d", purchaseAmount - discountedAmount)}")

    }
}