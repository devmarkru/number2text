package ru.devmark.converter.unit

import ru.devmark.converter.model.Gender

data class UnitInfo(
    override val gender: Gender,
    override val singleForm: String,
    override val severalForm: String,
    override val pluralForm: String,
): AbstractUnitInfo
