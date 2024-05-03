package ru.devmark.converter.unit

import ru.devmark.converter.model.Gender

enum class LengthUnitInfo : AbstractUnitInfo {
    METER {
        override val gender = Gender.MALE
        override val singleForm = "метр"
        override val severalForm = "метра"
        override val pluralForm = "метров"
    },
    CENTIMETER {
        override val gender = Gender.MALE
        override val singleForm = "сантиметр"
        override val severalForm = "сантиметра"
        override val pluralForm = "сантиметров"
    },
    KILOMETER {
        override val gender = Gender.MALE
        override val singleForm = "километр"
        override val severalForm = "километра"
        override val pluralForm = "километров"
    },
}
