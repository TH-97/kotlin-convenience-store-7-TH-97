package store.model

class Promotion(
    private val name: String,
    private val buy: String,
    private val get: String,
    private val startDate: String,
    private val endDate: String
) {
    fun getName(): String {
        return name
    }

    fun getBuy(): String {
        return buy
    }

    fun getGet(): String {
        return get
    }

    fun getStartDate(): String {
        return startDate
    }

    fun getEndDate(): String {
        return endDate
    }

}