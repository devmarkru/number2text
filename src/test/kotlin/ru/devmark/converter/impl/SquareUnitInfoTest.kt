package ru.devmark.converter.impl

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.devmark.converter.unit.SquareUnitInfo

class SquareUnitInfoTest {

    private val converter = Number2TextConverterImpl()

    @Test
    fun convertAr_0() {
        Assertions.assertEquals("0 аров", convertAr(0))
    }

    @Test
    fun convertAr_1() {
        Assertions.assertEquals("1 ар", convertAr(1))
    }

    @Test
    fun convertAr_2() {
        Assertions.assertEquals("2 ара", convertAr(2))
    }

    @Test
    fun convertAr_5() {
        Assertions.assertEquals("5 аров", convertAr(5))
    }

    @Test
    fun convertHectare_1() {
        Assertions.assertEquals("1 гектар", convertHectare(1))
    }

    @Test
    fun convertHectare_3() {
        Assertions.assertEquals("3 гектара", convertHectare(3))
    }

    @Test
    fun convertHectare_9() {
        Assertions.assertEquals("9 гектаров", convertHectare(9))
    }

    private fun convertAr(count: Long): String {
        val result = converter.convertNumberToWordsWithUnit(count, SquareUnitInfo.AR)
        return "$count ${result.unitWord}"
    }

    private fun convertHectare(count: Long): String {
        val result = converter.convertNumberToWordsWithUnit(count, SquareUnitInfo.HECTARE)
        return "$count ${result.unitWord}"
    }
}
