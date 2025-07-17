package ru.devmark.converter.impl

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.devmark.converter.unit.WeightUnitInfo

class WeightUnitInfoTest {

    private val converter = Number2TextConverterImpl()

    @Test
    fun convertGram_0() {
        Assertions.assertEquals("0 граммов", convertGram(0))
    }

    @Test
    fun convertGram_1() {
        Assertions.assertEquals("1 грамм", convertGram(1))
    }

    @Test
    fun convertGram_3() {
        Assertions.assertEquals("3 грамма", convertGram(3))
    }

    @Test
    fun convertGram_5() {
        Assertions.assertEquals("5 граммов", convertGram(5))
    }

    @Test
    fun convertKilogram_1() {
        Assertions.assertEquals("1 килограмм", convertKilogram(1))
    }

    @Test
    fun convertKilogram_4() {
        Assertions.assertEquals("4 килограмма", convertKilogram(4))
    }

    @Test
    fun convertKilogram_11() {
        Assertions.assertEquals("11 килограммов", convertKilogram(11))
    }

    @Test
    fun convertMilligram_2() {
        Assertions.assertEquals("2 миллиграмма", convertMilligram(2))
    }

    @Test
    fun convertMilligram_7() {
        Assertions.assertEquals("7 миллиграммов", convertMilligram(7))
    }

    private fun convertGram(count: Long): String {
        val result = converter.convertNumberToWordsWithUnit(count, WeightUnitInfo.GRAM)
        return "$count ${result.unitWord}"
    }

    private fun convertKilogram(count: Long): String {
        val result = converter.convertNumberToWordsWithUnit(count, WeightUnitInfo.KILOGRAM)
        return "$count ${result.unitWord}"
    }

    private fun convertMilligram(count: Long): String {
        val result = converter.convertNumberToWordsWithUnit(count, WeightUnitInfo.MILLIGRAM)
        return "$count ${result.unitWord}"
    }
}
