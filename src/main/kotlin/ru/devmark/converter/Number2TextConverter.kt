package ru.devmark.converter

import ru.devmark.converter.model.UnitInfo

interface Number2TextConverter {

    fun convertNumberUnitOnlyToText(number: Long, unit: UnitInfo): String

    fun convertNumberWithUnitToText(number: Long, unit: UnitInfo): String

    fun convertNumberToText(number: Long): String
}
