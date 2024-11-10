package store.service

import store.model.NormalProducts
import store.model.PromotionsProducts

class InitializationProducts {
    fun initializationProducts(
        promotionsProductsList: MutableList<PromotionsProducts>,
        normalProductList: MutableList<NormalProducts>
    ): Pair<MutableList<PromotionsProducts>, MutableList<NormalProducts>> {
        val normalProductNames = getNormalProductNames(normalProductList)

        for (promotionProduct in promotionsProductsList) {
            if (productNotInList(promotionProduct, normalProductNames)) {
                addProductToNormalList(promotionProduct, normalProductList)
            }
        }

        return Pair(promotionsProductsList, normalProductList)
    }

    private fun getNormalProductNames(normalProductList: List<NormalProducts>): List<String> {
        return normalProductList.map { it.getName() }
    }

    private fun productNotInList(
        promotionProduct: PromotionsProducts,
        normalProductNames: List<String>
    ): Boolean {
        return !normalProductNames.contains(promotionProduct.getName())
    }

    private fun addProductToNormalList(
        promotionProduct: PromotionsProducts,
        normalProductList: MutableList<NormalProducts>
    ) {
        normalProductList.add(
            NormalProducts(
                promotionProduct.getName(),
                promotionProduct.getPrice(),
                "0",
                "재고 없음"
            )
        )
    }
}