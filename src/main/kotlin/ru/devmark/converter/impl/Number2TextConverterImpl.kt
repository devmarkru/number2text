package ru.devmark.converter.impl

import ru.devmark.converter.Number2TextConverter
import ru.devmark.converter.unit.AbstractUnitInfo
import ru.devmark.converter.model.ConverterResult
import ru.devmark.converter.model.Gender
import ru.devmark.converter.model.ThousandInfo
import ru.devmark.converter.unit.UnitInfo
import kotlin.math.absoluteValue

class Number2TextConverterImpl : Number2TextConverter {

    override fun convertNumberToText(number: Long): String =
        convertNumberToWordsWithUnit(number, DEFAULT_DIMENSION).numberWords
            .joinToString(separator = " ")

    override fun convertNumberToTextWithUnit(number: Long, unit: AbstractUnitInfo): String {
        val result = convertNumberToWordsWithUnit(number, unit)
        return (result.numberWords + result.unitWord)
            .joinToString(separator = " ")
    }

    override fun convertNumberToWordsWithUnit(number: Long, unit: AbstractUnitInfo): ConverterResult =
        convertCountWithUnitToWords(number, unit)

    private fun convertCountWithUnitToWords(number: Long, unit: AbstractUnitInfo): ConverterResult {
        val dimensions = mutableListOf<AbstractUnitInfo>()
        dimensions.add(unit)
        dimensions.addAll(DIMENSIONS)
        var absNumber = number.absoluteValue
        var dimensionIndex = 0
        val thousands = mutableListOf<ThousandInfo>()
        do {
            val modulo = absNumber % 1000
            absNumber = absNumber / 1000
            if (modulo >= 0 || absNumber == 0L) {
                val dimension = dimensions[dimensionIndex]
                val thousandInfo = thousandToWords(modulo.toInt(), dimension)
                thousands.add(0, thousandInfo)
            }
            dimensionIndex++
        } while (dimensionIndex < dimensions.size && absNumber > 0)
        val parts = mutableListOf<String>()
        if (number < 0) {
            parts.add(0, "минус")
        }
        thousands.forEachIndexed { index, thousandInfo ->
            if (thousandInfo.numberValue > 0 || number == 0L) {
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
            UnitInfo(Gender.MALE, "триллион", "триллиона", "триллионов")
        )
    }
}
