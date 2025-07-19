package ru.devmark.converter

import ru.devmark.converter.model.ConverterResult
import ru.devmark.converter.unit.AbstractUnitInfo
import java.math.BigDecimal
import java.math.BigInteger

interface Number2TextConverter {

    fun convertNumberToText(number: Long): String

    fun convertNumberToTextWithUnit(number: Long, unit: AbstractUnitInfo): String

    fun convertNumberToWordsWithUnit(number: Long, unit: AbstractUnitInfo): ConverterResult

    fun convertNumberToText(number: BigInteger): String

    fun convertNumberToTextWithUnit(number: BigInteger, unit: AbstractUnitInfo): String

    fun convertNumberToWordsWithUnit(number: BigInteger, unit: AbstractUnitInfo): ConverterResult

    fun convertDecimalNumberToTextWithUnits(
        number: BigDecimal,
        integerUnit: AbstractUnitInfo,
        fractionalUnit: AbstractUnitInfo,
        fractionalRatio: Int,
    ): String

    fun convertDecimalNumberToWordsWithUnits(
        number: BigDecimal,
        integerUnit: AbstractUnitInfo,
        fractionalUnit: AbstractUnitInfo,
        fractionalRatio: Int,
    ): List<String>
}
