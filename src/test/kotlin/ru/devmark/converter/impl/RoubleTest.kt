package ru.devmark.converter.impl

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.devmark.converter.model.Currency

class RoubleTest {

    private val converter = Number2TextConverterImpl()

    @Test
    fun roubles2Text_0() {
        Assertions.assertEquals("ноль рублей", convertRouble(0))
    }

    @Test
    fun roubles2Text_1() {
        Assertions.assertEquals("один рубль", convertRouble(1))
    }

    @Test
    fun roubles2Text_2() {
        Assertions.assertEquals("два рубля", convertRouble(2))
    }

    @Test
    fun roubles2Text_4() {
        Assertions.assertEquals("четыре рубля", convertRouble(4))
    }

    @Test
    fun roubles2Text_9() {
        Assertions.assertEquals("девять рублей", convertRouble(9))
    }

    @Test
    fun roubles2Text_10() {
        Assertions.assertEquals("десять рублей", convertRouble(10))
    }

    @Test
    fun roubles2Text_12() {
        Assertions.assertEquals("двенадцать рублей", convertRouble(12))
    }

    @Test
    fun roubles2Text_20() {
        Assertions.assertEquals("двадцать рублей", convertRouble(20))
    }

    @Test
    fun roubles2Text_21() {
        Assertions.assertEquals("двадцать один рубль", convertRouble(21))
    }

    @Test
    fun roubles2Text_100() {
        Assertions.assertEquals("сто рублей", convertRouble(100))
    }

    @Test
    fun roubles2Text_1000() {
        Assertions.assertEquals("одна тысяча рублей", convertRouble(1000))
    }

    @Test
    fun roubles2Text_1_000_000() {
        Assertions.assertEquals("один миллион рублей", convertRouble(1_000_000))
    }

    @Test
    fun roubles2Text_3_000_000_000() {
        Assertions.assertEquals("три миллиарда рублей", convertRouble(3_000_000_000))
    }

    private fun convertRouble(amount: Long) =
        converter.convertNumberWithUnitToText(amount, Currency.RUB.unitInfo)
}
