# number2text
Библиотека для преобразования чисел в текстовое представление.

## Примеры
### Без единицы измерения
Чтобы получить простое текстовое представление числа, используйте метод `convertNumberToText()`:
```kotlin
val converter = Number2TextConverterImpl()
println(converter.convertNumberToText(123))
// "сто двадцать три"
```

### С единицей измерения
Если вместе с числом требуется просклонять какое-то существительное с учётом морфологии русского языка, то сначала нужно определить класс `UnitInfo`.

Например, для слова "статья" определение будет выглядеть так:

```kotlin
val ARTICLE_INFO = UnitInfo(
    gender = Gender.FEMALE,
    singleForm = "статья",
    severalForm = "статьи",
    pluralForm = "статей"
)
```

* Перечисление `gender` указывает род единицы измерения: мужской, женский или средний.
* `singleForm` - единичная форма измерения ("1 статья").
* `severalForm` - форма измерения от 2 до 4 единиц ("4 статьи").
* `pluralForm` - множественная форма ("5 статей").

Теперь этот объект вместе с числом передаём в метод `convertNumberWithUnitToText()`:
```kotlin
println(converter.convertNumberWithUnitToText(123, ARTICLE_INFO))
// "сто двадцать три статьи"
```

Если нужно просклонять только измерение, а число оставить числом, используйте `convertNumberUnitOnlyToText()`:
```kotlin
println(converter.convertNumberUnitOnlyToText(123, ARTICLE_INFO))
// "123 статьи"
```

### Валюта
Часто преобразование числа в текст используется для работы с валютой. Библиотека содержит набор предустановленных словоформ для валют `Currency`:

```kotlin
println(converter.convertNumberWithUnitToText(125, Currency.RUB.unitInfo))
// "сто двадцать пять рублей"
```
