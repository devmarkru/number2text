package ru.devmark.converter.unit

import ru.devmark.converter.model.Gender

enum class WeightUnitInfo : AbstractUnitInfo {
    GRAM {
        override val gender = Gender.MALE
        override val singleForm = "грамм"
        override val severalForm = "грамма"
        override val pluralForm = "граммов"
    },
    KILOGRAM {
        override val gender = Gender.MALE
        override val singleForm = "килограмм"
        override val severalForm = "килограмма"
        override val pluralForm = "килограммов"
    },
    MILLIGRAM {
        override val gender = Gender.MALE
        override val singleForm = "миллиграмм"
        override val severalForm = "миллиграмма"
        override val pluralForm = "миллиграммов"
    },
}
