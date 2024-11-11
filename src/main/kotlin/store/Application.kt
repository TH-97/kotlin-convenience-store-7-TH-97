package store

import camp.nextstep.edu.missionutils.DateTimes
import store.controller.ConvenienceController
import store.model.NormalProducts
import store.model.Promotion
import store.model.PromotionsProducts
import store.service.InitializationProducts
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main() {
    val promotionFilePath = "src/main/resources/promotions.md"
    val promotions = parsePromotionFile(promotionFilePath)
    val filePath = "src/main/resources/products.md"
    val (promotionsProductsList, normalProductList) = parseProductsFile(filePath, promotions)
    val (promotionsProductLists, normalProductLists) = saveProductsAndPromotion(
        promotionsProductsList as MutableList<PromotionsProducts>,
        normalProductList as MutableList<NormalProducts>,
    )
    ConvenienceController.ready(promotionsProductLists, normalProductLists, promotions)
}

fun saveProductsAndPromotion(
    promotionsProductsList: MutableList<PromotionsProducts>,
    normalProductList: MutableList<NormalProducts>,
): Pair<MutableList<PromotionsProducts>, MutableList<NormalProducts>> {
    val (pro, nor) = InitializationProducts().initializationProducts(
        promotionsProductsList,
        normalProductList
    )
    return Pair(pro, nor)
}

fun parseProductsFile(
    filePath: String,
    promotions: List<Promotion>
): Pair<List<PromotionsProducts>, List<NormalProducts>> {
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
            val statDate = promotions.find { it.getName() == promotion }?.getStartDate()
            val endDate = promotions.find { it.getName() == promotion }?.getEndDate()
            if (isOngoing(statDate, endDate)) {
                promotionsProducts.add(PromotionsProducts(name, price, quantity, promotion))
            }
        }
    }

    return Pair(promotionsProducts, normalProducts)
}

fun isOngoing(statDate: String?, endDate: String?): Boolean {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val currentDate = DateTimes.now().toLocalDate()

    val startDate = LocalDate.parse(statDate, formatter)
    val endDate = LocalDate.parse(endDate, formatter)
    
    return !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate)
}

fun parsePromotionFile(promotionFilePath: String): List<Promotion> {
    val lines = File(promotionFilePath).readLines().drop(1)
    val promotion = mutableListOf<Promotion>()

    lines.forEach { line ->
        val columns = line.split(",")
        val name = columns[0]
        val buy = columns[1]
        val get = columns[2]
        val startDate = columns[3]
        val endDate = columns[4]
        promotion.add(Promotion(name, buy, get, startDate, endDate))
    }
    return promotion
}




