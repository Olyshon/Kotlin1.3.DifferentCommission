import kotlin.math.roundToInt

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

    if (sumCur >= 150_000 || sumLast >= 600_000) println("Достигнут лимит перевода по карте")
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
            if (sumLast >= 75_000) ((0.006 * sumCur + 20) * 100).roundToInt() else 0
        }
        cardNum.toInt() == 3 || cardNum.toInt() == 4 -> {
            val minFeeVisaMir: Int = 35_00
            if (sumCur*0.75 < minFeeVisaMir) minFeeVisaMir else (sumCur*0.75).roundToInt()
        }
        cardNum.toInt() == 5  -> {
            if (sumCur < 15_000 && sumLast < 40_000) 0 else -1
        }
        else -> -2
    }
}