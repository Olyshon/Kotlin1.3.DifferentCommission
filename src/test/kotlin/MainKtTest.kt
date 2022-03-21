import org.junit.Test
import org.junit.Assert.*

class MainKtTest {

   @Test
    fun calcComm_mastercard_more_limit() {
        //Arrange
        val num = "1"
        val last = 76_000
        val cur = 1000

        //Act
        val result = calcComm(
            cardNum = num,
            sumLast = last,
            sumCur = cur
        )

        //Assert
        assertEquals(2600 , result)
    }

    @Test
    fun calcComm_maestro_less_limit() {
        //Arrange
        val num = "2"
        val last = 6_000
        val cur = 1000

        //Act
        val result = calcComm(
            cardNum = num,
            sumLast = last,
            sumCur = cur
        )

        //Assert
        assertEquals(56 , result)
    }

    @Test
    fun calcComm_visa_minFee() {
        //Arrange
        val num = "3"
        val last = 6_000
        val cur = 1000

        //Act
        val result = calcComm(
            cardNum = num,
            sumLast = last,
            sumCur = cur
        )

        //Assert
        assertEquals(35_00 , result)
    }

    @Test
    fun calcComm_visa_Fee() {
        //Arrange
        val num = "4"
        val last = 6_000
        val cur = 6_000

        //Act
        val result = calcComm(
            cardNum = num,
            sumLast = last,
            sumCur = cur
        )

        //Assert
        assertEquals(45_00 , result)
    }

    @Test
    fun calcComm_VKPay_without_fee() {
        //Arrange
        val num = "5"
        val last = 6_000
        val cur = 10_000

        //Act
        val result = calcComm(
            cardNum = num,
            sumLast = last,
            sumCur = cur
        )

        //Assert
        assertEquals(0 , result)
    }

    @Test
    fun calcComm_VKPay_more_limit1() {
        //Arrange
        val num = "5"
        val last = 41_000
        val cur = 10_000

        //Act
        val result = calcComm(
            cardNum = num,
            sumLast = last,
            sumCur = cur
        )

        //Assert
        assertEquals(-1 , result)
    }
    @Test
    fun calcComm_VKPay_more_limit2() {
        //Arrange
        val num = "5"
        val last = 1_000
        val cur = 16_000

        //Act
        val result = calcComm(
            cardNum = num,
            sumLast = last,
            sumCur = cur
        )

        //Assert
        assertEquals(-1 , result)
    }

    @Test
    fun calcComm_incorrect_card_number() {
        //Arrange
        val num = "7"
        val last = 0
        val cur = 0

        //Act
        val result = calcComm(
            cardNum = num,
            sumLast = last,
            sumCur = cur
        )

        //Assert
        assertEquals(-2 , result)
    }
}

