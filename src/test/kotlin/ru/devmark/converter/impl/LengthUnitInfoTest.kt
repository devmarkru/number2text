package ru.devmark.converter.impl

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.devmark.converter.unit.LengthUnitInfo

class LengthUnitInfoTest {

    private val converter = Number2TextConverterImpl()

    @Test
    fun convertMeter_0() {
        Assertions.assertEquals("0 метров", convertMeter(0))
    }

    @Test
    fun convertMeter_1() {
        Assertions.assertEquals("1 метр", convertMeter(1))
    }

    @Test
    fun convertMeter_3() {
        Assertions.assertEquals("3 метра", convertMeter(3))
    }

    @Test
    fun convertMeter_7() {
        Assertions.assertEquals("7 метров", convertMeter(7))
    }

    @Test
    fun convertCentimeter_0() {
        Assertions.assertEquals("0 сантиметров", convertCentimeter(0))
    }

    @Test
    fun convertCentimeter_1() {
        Assertions.assertEquals("1 сантиметр", convertCentimeter(1))
    }

    @Test
    fun convertCentimeter_3() {
        Assertions.assertEquals("3 сантиметра", convertCentimeter(3))
    }

    @Test
    fun convertCentimeter_5() {
        Assertions.assertEquals("5 сантиметров", convertCentimeter(5))
    }

    @Test
    fun convertKilometer_0() {
        Assertions.assertEquals("0 километров", convertKilometer(0))
    }

    @Test
    fun convertKilometer_1() {
        Assertions.assertEquals("1 километр", convertKilometer(1))
    }

    @Test
    fun convertKilometer_4() {
        Assertions.assertEquals("4 километра", convertKilometer(4))
    }

    @Test
    fun convertKilometer_11() {
        Assertions.assertEquals("11 километров", convertKilometer(11))
    }

    private fun convertMeter(count: Long): String {
        val result = converter.convertNumberToWordsWithUnit(count, LengthUnitInfo.METER)
        return "$count ${result.unitWord}"
    }

    private fun convertCentimeter(count: Long): String {
        val result = converter.convertNumberToWordsWithUnit(count, LengthUnitInfo.CENTIMETER)
        return "$count ${result.unitWord}"
    }

    private fun convertKilometer(count: Long): String {
        val result = converter.convertNumberToWordsWithUnit(count, LengthUnitInfo.KILOMETER)
        return "$count ${result.unitWord}"
    }
}