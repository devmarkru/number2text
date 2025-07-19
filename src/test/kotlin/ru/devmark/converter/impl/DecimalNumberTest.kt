package ru.devmark.converter.impl

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.devmark.converter.unit.CurrencyUnitInfo
import ru.devmark.converter.unit.LengthUnitInfo
import ru.devmark.converter.unit.UnitInfo
import ru.devmark.converter.model.Gender
import java.math.BigDecimal

class DecimalNumberTest {

    private val converter = Number2TextConverterImpl()

    @Test
    fun rublesAndKopecks_default() {
        val result = converter.convertDecimalNumberToTextWithUnits(
            BigDecimal("12.34"),
            CurrencyUnitInfo.RUB,
            UnitInfo(Gender.FEMALE, "копейка", "копейки", "копеек", asText = false),
            100,
        )
        Assertions.assertEquals("двенадцать рублей 34 копейки", result)
    }

    @Test
    fun rublesAndKopecks_digitsAndWords() {
        val result = converter.convertDecimalNumberToTextWithUnits(
            BigDecimal("12.34"),
            UnitInfo(Gender.MALE, "рубль", "рубля", "рублей", asText = false),
            CurrencyUnitInfo.KOPECK,
            100,
        )
        Assertions.assertEquals("12 рублей тридцать четыре копейки", result)
    }

    @Test
    fun rublesAndKopecks_allWords() {
        val result = converter.convertDecimalNumberToTextWithUnits(
            BigDecimal("12.34"),
            CurrencyUnitInfo.RUB,
            CurrencyUnitInfo.KOPECK,
            100,
        )
        Assertions.assertEquals("двенадцать рублей тридцать четыре копейки", result)
    }

    @Test
    fun rublesAndKopecks_allDigits() {
        val result = converter.convertDecimalNumberToTextWithUnits(
            BigDecimal("12.34"),
            UnitInfo(Gender.MALE, "рубль", "рубля", "рублей", asText = false),
            UnitInfo(Gender.FEMALE, "копейка", "копейки", "копеек", asText = false),
            100,
        )
        Assertions.assertEquals("12 рублей 34 копейки", result)
    }

    @Test
    fun kilometersAndMeters() {
        val result = converter.convertDecimalNumberToTextWithUnits(
            BigDecimal("1.234"),
            LengthUnitInfo.KILOMETER,
            UnitInfo(Gender.MALE, "метр", "метра", "метров", asText = false),
            1000,
        )
        Assertions.assertEquals("один километр 234 метра", result)
    }

    @Test
    fun wordListResult() {
        val result = converter.convertDecimalNumberToWordsWithUnits(
            BigDecimal("1.234"),
            UnitInfo(Gender.MALE, "километр", "километра", "километров", asText = false),
            UnitInfo(Gender.MALE, "метр", "метра", "метров", asText = false),
            1000,
        )
        Assertions.assertEquals(
            listOf("1", "километр", "234", "метра"),
            result,
        )
    }

    @Test
    fun trillionRubles() {
        val result = converter.convertDecimalNumberToTextWithUnits(
            BigDecimal("1000000000000.99"),
            CurrencyUnitInfo.RUB,
            CurrencyUnitInfo.KOPECK,
            100,
        )
        Assertions.assertEquals(
            "один триллион рублей девяносто девять копеек",
            result,
        )
    }

    @Test
    fun twoQuadrillionRubles() {
        val result = converter.convertDecimalNumberToTextWithUnits(
            BigDecimal("2000000000000000.01"),
            CurrencyUnitInfo.RUB,
            CurrencyUnitInfo.KOPECK,
            100,
        )
        Assertions.assertEquals(
            "два квадриллиона рублей одна копейка",
            result,
        )
    }
}
