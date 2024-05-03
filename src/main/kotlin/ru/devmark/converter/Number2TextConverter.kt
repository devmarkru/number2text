package ru.devmark.converter

import ru.devmark.converter.model.ConverterResult
import ru.devmark.converter.unit.AbstractUnitInfo

interface Number2TextConverter {

    fun convertNumberToText(number: Long): String

    fun convertNumberToTextWithUnit(number: Long, unit: AbstractUnitInfo): String

    fun convertNumberToWordsWithUnit(number: Long, unit: AbstractUnitInfo): ConverterResult
}
