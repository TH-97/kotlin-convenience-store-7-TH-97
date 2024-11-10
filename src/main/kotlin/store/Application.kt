package store

import store.controller.ConvenienceController
import store.model.NormalProducts
import store.model.PromotionsProducts
import java.io.File

fun main() {
    val filePath = "src/main/resources/products.md"
    val (PromotionsProductsList, NormalProductList) = parseProductsFile(filePath)
    ConvenienceController().saveProducts(PromotionsProductsList, NormalProductList)
    ConvenienceController().openConvenience()
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




