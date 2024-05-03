package ru.devmark.converter.unit

import ru.devmark.converter.model.Gender

interface AbstractUnitInfo {
    val gender: Gender
    val singleForm: String
    val severalForm: String
    val pluralForm: String
}
