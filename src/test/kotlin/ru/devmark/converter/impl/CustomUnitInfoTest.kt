package ru.devmark.converter.impl

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.devmark.converter.model.Gender
import ru.devmark.converter.model.UnitInfo

class CustomUnitInfoTest {

    private val converter = Number2TextConverterImpl()

    @Test
    fun starCount2Text_0() {
        Assertions.assertEquals("0 звёзд", convertStar(0))
    }

    @Test
    fun starCount2Text_1() {
        Assertions.assertEquals("1 звезда", convertStar(1))
    }

    @Test
    fun starCount2Text_2() {
        Assertions.assertEquals("2 звезды", convertStar(2))
    }

    @Test
    fun starCount2Text_4() {
        Assertions.assertEquals("4 звезды", convertStar(4))
    }

    @Test
    fun starCount2Text_9() {
        Assertions.assertEquals("9 звёзд", convertStar(9))
    }

    @Test
    fun starCount2Text_10() {
        Assertions.assertEquals("10 звёзд", convertStar(10))
    }

    @Test
    fun starCount2Text_12() {
        Assertions.assertEquals("12 звёзд", convertStar(12))
    }

    @Test
    fun starCount2Text_20() {
        Assertions.assertEquals("20 звёзд", convertStar(20))
    }

    @Test
    fun starCount2Text_21() {
        Assertions.assertEquals("21 звезда", convertStar(21))
    }

    @Test
    fun starCount2Text_100() {
        Assertions.assertEquals("100 звёзд", convertStar(100))
    }

    @Test
    fun starCount2Text_1000() {
        Assertions.assertEquals("1000 звёзд", convertStar(1000))
    }

    @Test
    fun starCount2Text_1_000_000() {
        Assertions.assertEquals("1000000 звёзд", convertStar(1_000_000))
    }

    private fun convertStar(count: Long) =
        converter.convertNumberUnitOnlyToText(count, STAR_INFO)

    private companion object {
        val STAR_INFO = UnitInfo(
            gender = Gender.FEMALE,
            singleForm = "звезда",
            severalForm = "звезды",
            pluralForm = "звёзд"
        )
    }
}
