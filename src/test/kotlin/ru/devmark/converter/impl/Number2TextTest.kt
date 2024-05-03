package ru.devmark.converter.impl

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.devmark.converter.Number2TextConverter

class Number2TextTest {

    private val converter: Number2TextConverter = Number2TextConverterImpl()

    @Test
    fun number2Text_minus_12() {
        Assertions.assertEquals("минус двенадцать", convertNumber(-12))
    }

    @Test
    fun number2Text_0() {
        Assertions.assertEquals("ноль", convertNumber(0))
    }

    @Test
    fun number2Text_1() {
        Assertions.assertEquals("один", convertNumber(1))
    }

    @Test
    fun number2Text_2() {
        Assertions.assertEquals("два", convertNumber(2))
    }

    @Test
    fun number2Text_5() {
        Assertions.assertEquals("пять", convertNumber(5))
    }

    @Test
    fun number2Text_10() {
        Assertions.assertEquals("десять", convertNumber(10))
    }

    @Test
    fun number2Text_11() {
        Assertions.assertEquals("одиннадцать", convertNumber(11))
    }

    @Test
    fun number2Text_20() {
        Assertions.assertEquals("двадцать", convertNumber(20))
    }

    @Test
    fun number2Text_21() {
        Assertions.assertEquals("двадцать один", convertNumber(21))
    }

    @Test
    fun number2Text_100() {
        Assertions.assertEquals("сто", convertNumber(100))
    }

    @Test
    fun number2Text_123() {
        println(convertNumber(123))
        Assertions.assertEquals("сто двадцать три", convertNumber(123))
    }

    @Test
    fun number2Text_305() {
        Assertions.assertEquals("триста пять", convertNumber(305))
    }

    @Test
    fun number2Text_1234() {
        Assertions.assertEquals("одна тысяча двести тридцать четыре", convertNumber(1234))
    }

    @Test
    fun number2Text_2034() {
        Assertions.assertEquals("две тысячи тридцать четыре", convertNumber(2034))
    }

    @Test
    fun number2Text_5006() {
        Assertions.assertEquals("пять тысяч шесть", convertNumber(5006))
    }

    @Test
    fun number2Text_1_000_000() {
        Assertions.assertEquals("один миллион", convertNumber(1_000_000))
    }

    @Test
    fun number2Text_3_000_000() {
        Assertions.assertEquals("три миллиона", convertNumber(3_000_000))
    }

    @Test
    fun number2Text_9_000_000() {
        Assertions.assertEquals("девять миллионов", convertNumber(9_000_000))
    }

    @Test
    fun number2Text_1_000_000_000() {
        Assertions.assertEquals("один миллиард", convertNumber(1_000_000_000))
    }

    @Test
    fun number2Text_2_000_000_000() {
        Assertions.assertEquals("два миллиарда", convertNumber(2_000_000_000))
    }

    @Test
    fun number2Text_6_000_000_000() {
        Assertions.assertEquals("шесть миллиардов", convertNumber(6_000_000_000))
    }

    @Test
    fun number2Text_1_000_000_000_000() {
        Assertions.assertEquals("один триллион", convertNumber(1_000_000_000_000))
    }

    @Test
    fun number2Text_4_000_000_000_000() {
        Assertions.assertEquals("четыре триллиона", convertNumber(4_000_000_000_000))
    }

    @Test
    fun number2Text_8_000_000_000_000() {
        Assertions.assertEquals("восемь триллионов", convertNumber(8_000_000_000_000))
    }

    private fun convertNumber(number: Long) =
        converter.convertNumberToText(number)
}
