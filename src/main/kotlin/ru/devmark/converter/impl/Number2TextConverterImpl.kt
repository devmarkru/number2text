package ru.devmark.converter.impl

import ru.devmark.converter.Number2TextConverter
import ru.devmark.converter.model.Gender
import ru.devmark.converter.model.ThousandInfo
import ru.devmark.converter.model.UnitInfo
import kotlin.math.absoluteValue

class Number2TextConverterImpl : Number2TextConverter {

    override fun convertNumberUnitOnlyToText(number: Long, unit: UnitInfo): String {
        val lastPart = convertCountWithUnitToWords(number, unit).last()
        return "$number $lastPart"
    }

    override fun convertNumberWithUnitToText(number: Long, unit: UnitInfo): String {
        return convertCountWithUnitToWords(number, unit)
            .joinToString(separator = " ")
    }

    override fun convertNumberToText(number: Long): String {
        return convertCountWithUnitToWords(number, DEFAULT_DIMENSION)
            .joinToString(separator = " ")
    }

    private fun convertCountWithUnitToWords(number: Long, unit: UnitInfo): List<String> {
        val dimensions = mutableListOf<UnitInfo>()
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
                parts.add(thousandInfo.numberText)
            }
            if (thousandInfo.dimension.isNotBlank() && (thousandInfo.numberValue > 0 || index == thousands.size - 1)) {
                parts.add(thousandInfo.dimension)
            }
        }
        return parts
    }

    private fun thousandToWords(unsignedNumber: Int, unitInfo: UnitInfo): ThousandInfo {
        val parts = mutableListOf<String>()
        var modulo = unsignedNumber
        if (modulo == 0) {
            parts += ZERO
        } else {
            parts += HUNDREDS[modulo / 100]
            modulo = modulo % 100
            if (modulo in 11..19) {
                parts += SECOND_DOZEN[modulo % 10]
            } else {
                if (modulo == 10 || modulo >= 20) {
                    parts += DOZENS[modulo / 10]
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
            numberText = parts.filter { it.isNotEmpty() }.joinToString(separator = " "),
            dimension = form
        )
    }

    private companion object {
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
        val DOZENS = listOf(
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
        val SECOND_DOZEN = listOf(
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
        const val ZERO = "ноль"
        val DEFAULT_DIMENSION = UnitInfo(Gender.MALE, "", "", "")
        val DIMENSIONS = listOf(
            UnitInfo(Gender.FEMALE, "тысяча", "тысячи", "тысяч"),
            UnitInfo(Gender.MALE, "миллион", "миллиона", "миллионов"),
            UnitInfo(Gender.MALE, "миллиард", "миллиарда", "миллиардов"),
            UnitInfo(Gender.MALE, "триллион", "триллиона", "триллионов")
        )
    }
}
