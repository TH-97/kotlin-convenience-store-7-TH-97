package store.utils

import store.controller.ConvenienceController

class Validator {
    fun validatePurchasedProduct(purchasedProduct: String) {
        val regex = """\[[\w가-힣]+-\d+]""".toRegex()

        val items = purchasedProduct.split(",").map { it.trim() }

        items.map { item ->
            require(item.matches(regex)) { "[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요." }
            val content = item.substring(1, item.length - 1)
            val parts = content.split("-")
            val productName = parts[0]
            val productQuality = parts[1].toInt()
            require(ConvenienceController.checkProduct(productName)) { "[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요." }

            require(
                ConvenienceController.checkProductQuality(
                    productName,
                    productQuality
                )
            ) { "[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요." }
        }
    }
}