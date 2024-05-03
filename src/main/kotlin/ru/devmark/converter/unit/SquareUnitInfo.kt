package ru.devmark.converter.unit

import ru.devmark.converter.model.Gender

enum class SquareUnitInfo : AbstractUnitInfo {
    AR {
        override val gender = Gender.MALE
        override val singleForm = "ар"
        override val severalForm = "ара"
        override val pluralForm = "аров"
    },
    HECTARE {
        override val gender = Gender.MALE
        override val singleForm = "гектар"
        override val severalForm = "гектара"
        override val pluralForm = "гектаров"
    },
}
