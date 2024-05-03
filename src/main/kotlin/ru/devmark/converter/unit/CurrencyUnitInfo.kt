package ru.devmark.converter.unit

import ru.devmark.converter.model.Gender

enum class CurrencyUnitInfo : AbstractUnitInfo {
    RUB {
        override val gender = Gender.MALE
        override val singleForm = "рубль"
        override val severalForm = "рубля"
        override val pluralForm = "рублей"
    },
    KOPECK {
        override val gender = Gender.FEMALE
        override val singleForm = "копейка"
        override val severalForm = "копейки"
        override val pluralForm = "копеек"
    },
    USD {
        override val gender = Gender.MALE
        override val singleForm = "доллар"
        override val severalForm = "доллара"
        override val pluralForm = "долларов"
    },
    CENT {
        override val gender = Gender.MALE
        override val singleForm = "цент"
        override val severalForm = "цента"
        override val pluralForm = "центов"
    },
    EUR {
        override val gender = Gender.MALE
        override val singleForm = "евро"
        override val severalForm = "евро"
        override val pluralForm = "евро"
    },
    EURO_CENT {
        override val gender = Gender.MALE
        override val singleForm = "евроцент"
        override val severalForm = "евроцента"
        override val pluralForm = "евроцентов"
    },
}
