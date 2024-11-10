package store.service

import store.model.NormalProducts
import store.model.PromotionsProducts

class InitializationProducts {
    fun initializationProducts(
        promotionsProductsList: List<PromotionsProducts>,
        normalProductList: MutableList<NormalProducts> // MutableList로 변경하여 수정 가능하게 함
    ) {
        val normalProductNames = normalProductList.map { it.getName() }

        for (promotionProduct in promotionsProductsList) {
            if (!normalProductNames.any { promotionProduct.getName().contains(it) }) {
                println("매칭되지 않은 제품이 있습니다: ${promotionProduct.getName()}")
                normalProductList.add(
                    NormalProducts(
                        promotionProduct.getName(),
                        promotionProduct.getPrice(),
                        "0", // 수량이 없는 것으로 초기화
                        "재고 없음" // 상태를 "재고 없음"으로 설정
                    )
                )
            }
        }
    }
}