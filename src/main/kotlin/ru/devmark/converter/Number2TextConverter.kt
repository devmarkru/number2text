package ru.devmark.converter

import ru.devmark.converter.model.ConverterResult
import ru.devmark.converter.unit.AbstractUnitInfo

interface Number2TextConverter {

    fun convertNumberToText(number: Long): String

    fun convertNumberToTextWithUnit(number: Long, unit: AbstractUnitInfo): String

    fun convertNumberToWordsWithUnit(number: Long, unit: AbstractUnitInfo): ConverterResult

    fun convertDecimalNumberToTextWithUnits(
        number: java.math.BigDecimal,
        integerUnit: AbstractUnitInfo,
        fractionalUnit: AbstractUnitInfo,
        fractionalRatio: Int,
    ): String

    fun convertDecimalNumberToWordsWithUnits(
        number: java.math.BigDecimal,
        integerUnit: AbstractUnitInfo,
        fractionalUnit: AbstractUnitInfo,
        fractionalRatio: Int,
    ): List<String>
}
