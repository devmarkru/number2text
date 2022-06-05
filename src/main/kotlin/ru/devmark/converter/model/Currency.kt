package ru.devmark.converter.model

enum class Currency(val unitInfo: UnitInfo) {

    RUB(
        UnitInfo(
            gender = Gender.MALE,
            singleForm = "рубль",
            severalForm = "рубля",
            pluralForm = "рублей",
        )
    ),
    USD(
        UnitInfo(
            gender = Gender.MALE,
            singleForm = "доллар",
            severalForm = "доллара",
            pluralForm = "долларов",
        )
    ),
    EUR(
        UnitInfo(
            gender = Gender.MIDDLE,
            singleForm = "евро",
            severalForm = "евро",
            pluralForm = "евро",
        )
    ),
    KOPECK(
        UnitInfo(
            gender = Gender.FEMALE,
            singleForm = "копейка",
            severalForm = "копейки",
            pluralForm = "копеек",
        )
    ),
    CENT(
        UnitInfo(
            gender = Gender.MALE,
            singleForm = "цент",
            severalForm = "цента",
            pluralForm = "центов",
        )
    )
}
