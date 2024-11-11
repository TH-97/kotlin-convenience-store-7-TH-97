package store.model

import camp.nextstep.edu.missionutils.DateTimes
import java.time.LocalDateTime

class Promotion(
    private val name: String,
    private val buy: String,
    private val get: String,
    private val startDate: String,
    private val endDate: String
) {

    // 프로모션이 진행 중인지 확인하는 메서드
    fun isOngoing(): Boolean {
        val currentDateTime = DateTimes.now()  // 현재 날짜와 시간 가져오기
        val startDateTime = LocalDateTime.parse(startDate)  // 시작 날짜 파싱
        val endDateTime = LocalDateTime.parse(endDate)  // 종료 날짜 파싱

        return currentDateTime.isAfter(startDateTime) && currentDateTime.isBefore(endDateTime)
    }

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