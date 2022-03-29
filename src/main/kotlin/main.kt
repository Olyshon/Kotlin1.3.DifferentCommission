import kotlin.math.roundToInt

const val DAY_LIMIT_ALL = 150_000
const val MONTH_LIMIT_ALL = 600_000
const val MAX_VKPAY_ONE_TIME = 15_000
const val MAX_VKPAY_MONTH = 40_000
const val MAX_MAESTRO_MASTERCARD_MONTH = 75_000
const val FEE_PERC_MAESTRO_MASTERCARD = 0.006
const val FEE_PERC_VISA_MIR = 0.75


fun main() {
    println("Тип карты:")
    println("1. mastercard")
    println("2. maestro")
    println("3. visa")
    println("4. МИР")
    println("5. VK Pay")
    print("Введите номер:")
    val cardNum: String = readLine() ?: return
    print("Введите сумму предыдущих переводов в этом месяце (руб):")
    val sumLastIn: String = readLine() ?: return
    val sumLast: Int = sumLastIn.toInt()
    print("Введите сумму совершаемого перевода (руб):")
    val sumCurIn: String = readLine() ?: return
    val sumCur: Int = sumCurIn.toInt()

    if (sumCur >= DAY_LIMIT_ALL || sumLast >= MONTH_LIMIT_ALL) println("Достигнут лимит перевода по карте")
    else {
        when (val resultFee: Int = calcComm(cardNum, sumLast, sumCur)) {
            -1 -> println("Достигнут лимит по переводам VK Pay")
            -2 -> println("Введен некорректный номер карты")
            else -> println("Комиссия за перевод составит $resultFee коп")
        }
    }
}

fun calcComm(cardNum: String = "5", sumLast: Int = 0, sumCur: Int): Int {
    return when {
        cardNum.toInt() == 1 || cardNum.toInt() == 2 -> {
            val minFeeMaesMC = 20
            if (sumLast >= MAX_MAESTRO_MASTERCARD_MONTH) ((FEE_PERC_MAESTRO_MASTERCARD * sumCur + minFeeMaesMC) * 100).roundToInt() else 0
        }
        cardNum.toInt() == 3 || cardNum.toInt() == 4 -> {
            val minFeeVisaMir = 35_00
            if (sumCur*FEE_PERC_VISA_MIR < minFeeVisaMir) minFeeVisaMir else (sumCur*FEE_PERC_VISA_MIR).roundToInt()
        }
        cardNum.toInt() == 5  -> {
            if (sumCur < MAX_VKPAY_ONE_TIME && sumLast < MAX_VKPAY_MONTH) 0 else -1
        }
        else -> -2
    }
}