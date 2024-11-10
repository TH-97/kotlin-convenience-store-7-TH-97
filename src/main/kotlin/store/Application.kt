package store

import store.model.NormalProducts
import store.model.PromotionsProducts
import java.io.File

fun main() {
    val filePath = "src/main/resources/products.md"
    val (PromotionsProductsList, NormalProductList) = parseProductsFile(filePath)
//    displayProducts(NormalProductList)
//    displayProducts2(PromotionsProductsList)
}

fun parseProductsFile(filePath: String): Pair<List<PromotionsProducts>, List<NormalProducts>> {
    val lines = File(filePath).readLines().drop(1)
    val promotionsProducts = mutableListOf<PromotionsProducts>()
    val normalProducts = mutableListOf<NormalProducts>()

    lines.forEach { line ->
        val columns = line.split(",")
        val name = columns[0]
        val price = columns[1]
        val quantity = columns[2]
        val promotion = columns[3]
        if (promotion == "null") {
            normalProducts.add(NormalProducts(name, price, quantity, promotion))
        }
        if (promotion != "null") {
            promotionsProducts.add(PromotionsProducts(name, price, quantity, promotion))
        }
    }

    return Pair(promotionsProducts, normalProducts)
}

//fun displayProducts(products: List<NormalProducts>) {
//    for (product in products) {
//        println(product.display())
//    }
//}

//fun displayProducts2(products: List<PromotionsProducts>) {
//    for (product in products) {
//        println(product.display())
//    }
//}



