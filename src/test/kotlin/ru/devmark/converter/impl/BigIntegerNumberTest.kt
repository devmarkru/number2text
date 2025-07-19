package ru.devmark.converter.impl

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.devmark.converter.Number2TextConverter
import ru.devmark.converter.unit.CurrencyUnitInfo
import java.math.BigInteger

class BigIntegerNumberTest {

    private val converter: Number2TextConverter = Number2TextConverterImpl()

    @Test
    fun oneQuadrillion() {
        Assertions.assertEquals(
            "один квадриллион",
            converter.convertNumberToText(BigInteger.TEN.pow(15))
        )
    }

    @Test
    fun twoQuintillion() {
        val number = BigInteger.TEN.pow(18) * BigInteger.valueOf(2)
        Assertions.assertEquals(
            "два квинтиллиона",
            converter.convertNumberToText(number)
        )
    }

    @Test
    fun decillion() {
        val number = BigInteger.TEN.pow(33)
        Assertions.assertEquals(
            "один дециллион",
            converter.convertNumberToText(number)
        )
    }

    @Test
    fun negativeSextillion() {
        val number = BigInteger.TEN.pow(21).negate() * BigInteger.valueOf(3)
        Assertions.assertEquals(
            "минус три секстиллиона",
            converter.convertNumberToText(number)
        )
    }

    @Test
    fun quintillionWithUnit() {
        val number = BigInteger.TEN.pow(18)
        Assertions.assertEquals(
            "один квинтиллион долларов",
            converter.convertNumberToTextWithUnit(number, CurrencyUnitInfo.USD)
        )
    }
}
