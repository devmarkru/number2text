package ru.devmark.converter.impl

import ru.devmark.converter.Number2TextConverter
import ru.devmark.converter.unit.AbstractUnitInfo
import ru.devmark.converter.model.ConverterResult
import ru.devmark.converter.model.Gender
import ru.devmark.converter.model.ThousandInfo
import ru.devmark.converter.unit.UnitInfo
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import kotlin.math.absoluteValue

class Number2TextConverterImpl : Number2TextConverter {

    override fun convertNumberToText(number: Long): String =
        convertNumberToText(BigInteger.valueOf(number))

    override fun convertNumberToText(number: BigInteger): String =
        convertNumberToWordsWithUnit(number, DEFAULT_DIMENSION).numberWords
            .joinToString(separator = " ")

    override fun convertNumberToTextWithUnit(number: Long, unit: AbstractUnitInfo): String {
        val result = convertNumberToWordsWithUnit(BigInteger.valueOf(number), unit)
        return (result.numberWords + result.unitWord)
            .joinToString(separator = " ")
    }

    override fun convertNumberToTextWithUnit(number: BigInteger, unit: AbstractUnitInfo): String {
        val result = convertNumberToWordsWithUnit(number, unit)
        return (result.numberWords + result.unitWord)
            .joinToString(separator = " ")
    }

    override fun convertNumberToWordsWithUnit(number: Long, unit: AbstractUnitInfo): ConverterResult =
        convertNumberToWordsWithUnit(BigInteger.valueOf(number), unit)

    override fun convertNumberToWordsWithUnit(number: BigInteger, unit: AbstractUnitInfo): ConverterResult =
        convertCountWithUnitToWords(number, unit)

    override fun convertDecimalNumberToTextWithUnits(
        number: BigDecimal,
        integerUnit: AbstractUnitInfo,
        fractionalUnit: AbstractUnitInfo,
        fractionalRatio: Int,
    ): String =
        convertDecimalNumberToWordsWithUnits(
            number,
            integerUnit,
            fractionalUnit,
            fractionalRatio,
        ).joinToString(separator = " ")

    override fun convertDecimalNumberToWordsWithUnits(
        number: BigDecimal,
        integerUnit: AbstractUnitInfo,
        fractionalUnit: AbstractUnitInfo,
        fractionalRatio: Int,
    ): List<String> {
        val scale = fractionalRatio.toString().length - 1
        if (number !in BigDecimal.valueOf(Long.MIN_VALUE)..BigDecimal.valueOf(Long.MAX_VALUE)) {
            throw RuntimeException("The number is too large!")
        }
        val scaled = number.setScale(scale, RoundingMode.HALF_UP)

        val integerValue = scaled.toLong()
        val fractionalValue = scaled.abs().unscaledValue().toLong() % fractionalRatio

        val integerResult = convertNumberToWordsWithUnit(integerValue, integerUnit)
        val fractionalResult = convertNumberToWordsWithUnit(fractionalValue, fractionalUnit)

        val words = mutableListOf<String>()
        if (integerUnit.asText) {
            words.addAll(integerResult.numberWords)
        } else {
            if (integerValue < 0) {
                words.add("минус")
            }
            words.add(integerValue.absoluteValue.toString())
        }
        words.add(integerResult.unitWord)

        if (fractionalUnit.asText) {
            words.addAll(fractionalResult.numberWords)
        } else {
            words.add(fractionalValue.toString())
        }
        words.add(fractionalResult.unitWord)

        return words
    }

    private fun convertCountWithUnitToWords(number: Long, unit: AbstractUnitInfo): ConverterResult =
        convertCountWithUnitToWords(BigInteger.valueOf(number), unit)

    private fun convertCountWithUnitToWords(number: BigInteger, unit: AbstractUnitInfo): ConverterResult {
        val dimensions = mutableListOf<AbstractUnitInfo>()
        dimensions.add(unit)
        dimensions.addAll(DIMENSIONS)
        var absNumber = number.abs()
        var dimensionIndex = 0
        val thousands = mutableListOf<ThousandInfo>()
        val thousand = BigInteger.valueOf(1000)
        do {
            val modulo = absNumber.mod(thousand).toInt()
            absNumber = absNumber.divide(thousand)
            val dimension = dimensions[dimensionIndex]
            val thousandInfo = thousandToWords(modulo, dimension)
            thousands.add(0, thousandInfo)
            dimensionIndex++
        } while (dimensionIndex < dimensions.size && absNumber > BigInteger.ZERO)
        val parts = mutableListOf<String>()
        if (number.signum() < 0) {
            parts.add(0, "минус")
        }
        thousands.forEachIndexed { index, thousandInfo ->
            if (thousandInfo.numberValue > 0 || number == BigInteger.ZERO) {
                parts.addAll(thousandInfo.numberWords)
            }
            if (thousandInfo.dimension.isNotBlank() && thousandInfo.numberValue > 0 && index < thousands.lastIndex) {
                parts.add(thousandInfo.dimension)
            }
        }
        return ConverterResult(
            numberWords = parts,
            unitWord = thousands.lastOrNull()?.dimension ?: "",
        )
    }

    private fun thousandToWords(unsignedNumber: Int, unitInfo: AbstractUnitInfo): ThousandInfo {
        val parts = mutableListOf<String>()
        var modulo = unsignedNumber
        if (modulo == 0) {
            parts += ZERO
        } else {
            parts += HUNDREDS[modulo / 100]
            modulo = modulo % 100
            if (modulo in 11..19) {
                parts += SECOND_TEN[modulo % 10]
            } else {
                if (modulo == 10 || modulo >= 20) {
                    parts += TENS[modulo / 10]
                    modulo = modulo % 10
                }
                parts += when (unitInfo.gender) {
                    Gender.MALE -> MALE_DIGITS[modulo]
                    Gender.FEMALE -> FEMALE_DIGITS[modulo]
                    Gender.MIDDLE -> MIDDLE_DIGITS[modulo]
                }
            }
        }
        val form = when (modulo) {
            1 -> unitInfo.singleForm
            in 2..4 -> unitInfo.severalForm
            in 5..20, 0 -> unitInfo.pluralForm
            else -> throw RuntimeException("Modulo must be in 0..20: $modulo")
        }
        return ThousandInfo(
            numberValue = unsignedNumber,
            numberWords = parts.filter { it.isNotEmpty() },
            dimension = form,
        )
    }

    private companion object {
        const val ZERO = "ноль"

        val HUNDREDS = listOf(
            "",
            "сто",
            "двести",
            "триста",
            "четыреста",
            "пятьсот",
            "шестьсот",
            "семьсот",
            "восемьсот",
            "девятьсот"
        )
        val TENS = listOf(
            "",
            "десять",
            "двадцать",
            "тридцать",
            "сорок",
            "пятьдесят",
            "шестьдесят",
            "семьдесят",
            "восемьдесят",
            "девяносто"
        )
        val SECOND_TEN = listOf(
            "",
            "одиннадцать",
            "двенадцать",
            "тринадцать",
            "четырнадцать",
            "пятнадцать",
            "шестнадцать",
            "семнадцать",
            "восемнадцать",
            "девятнадцать"
        )
        val MALE_DIGITS = listOf("", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
        val FEMALE_DIGITS = listOf("", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
        val MIDDLE_DIGITS = listOf("", "одно", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
        val DEFAULT_DIMENSION = UnitInfo(Gender.MALE, "", "", "")
        val DIMENSIONS = listOf(
            UnitInfo(Gender.FEMALE, "тысяча", "тысячи", "тысяч"),
            UnitInfo(Gender.MALE, "миллион", "миллиона", "миллионов"),
            UnitInfo(Gender.MALE, "миллиард", "миллиарда", "миллиардов"),
            UnitInfo(Gender.MALE, "триллион", "триллиона", "триллионов"),
            UnitInfo(Gender.MALE, "квадриллион", "квадриллиона", "квадриллионов"),
            UnitInfo(Gender.MALE, "квинтиллион", "квинтиллиона", "квинтиллионов"),
            UnitInfo(Gender.MALE, "секстиллион", "секстиллиона", "секстиллионов"),
            UnitInfo(Gender.MALE, "септиллион", "септиллиона", "септиллионов"),
            UnitInfo(Gender.MALE, "октиллион", "октиллиона", "октиллионов"),
            UnitInfo(Gender.MALE, "нониллион", "нониллиона", "нониллионов"),
            UnitInfo(Gender.MALE, "дециллион", "дециллиона", "дециллионов"),
        )
    }
}
