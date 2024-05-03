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
Если вместе с числом требуется просклонять какое-то существительное с учётом морфологии русского языка, то сначала нужно определить экземпляр класса `UnitInfo`.

Например, для слова "статья" определение будет выглядеть так:

```kotlin
val ARTICLE_INFO = UnitInfo(
    gender = Gender.FEMALE,
    singleForm = "статья",
    severalForm = "статьи",
    pluralForm = "статей",
)
```

* перечисление `Gender` указывает род единицы измерения: мужской, женский или средний.
* `singleForm` - единичная форма измерения ("1 статья").
* `severalForm` - форма измерения от 2 до 4 единиц ("4 статьи").
* `pluralForm` - множественная форма ("5 статей").

Теперь этот объект вместе с числом передаём в метод `convertNumberWithUnitToText()`:

```kotlin
println(converter.convertNumberWithUnitToText(123, ARTICLE_INFO))
// "сто двадцать три статьи"
```

Если нужно просклонять только измерение, а число оставить числом, используйте `convertNumberToWordsWithUnit()`:
```kotlin
val number = 123L
val result = converter.convertNumberToWordsWithUnit(number, ARTICLE_INFO)
println("$number ${result.unitWord}")
// "123 статьи"
```

### Стандартные валюты и другие единицы измерения
Часто преобразование числа в текст используется для работы с валютой. Библиотека содержит набор предустановленных словоформ для валют `CurrencyUnitInfo`:

```kotlin
println(converter.convertNumberToTextWithUnit(125, CurrencyUnitInfo.RUB))
// "сто двадцать пять рублей"
```

Также там можно найти доллары, евро, центы и копейки.

Помимо валют вместе с библиотекой идут следующие стандартные единицы измерения:
* `LengthUnitInfo` - единицы измерения длины: метры, сантиметры и километры
* `SquareUnitInfo` - единицы измерения площади: ары и гектары
* `WeightUnitInfo` - единицы измерения веса: граммы, килограммы и миллиграммы

### Комплексный пример
Рассмотрим более сложный пример. Предположим, вы хотите записать в виде текста сумму, представленную десятичной дробью типа `BigDecimal`. Целая часть - рубли, а дробная - копейки. Для решения этой задачи можно написать следующий метод расширения:

```kotlin
fun BigDecimal.toTextWithUnits(
    integerUnitInfo: AbstractUnitInfo,
    fractionalUnitInfo: AbstractUnitInfo,
): String {
    if (this !in Long.MIN_VALUE.toBigDecimal()..Long.MAX_VALUE.toBigDecimal()) {
        throw RuntimeException("The number is too large!")
    }
    val amount = this.setScale(2, RoundingMode.HALF_UP)

    val integerResult = converter.convertNumberToWordsWithUnit(amount.toLong(), integerUnitInfo)
    val fractionalValue = amount.abs().unscaledValue().toLong() % 100
    val fractionalResult = converter.convertNumberToWordsWithUnit(fractionalValue, fractionalUnitInfo)
    val words = mutableListOf<String>()
    words.addAll(integerResult.numberWords)
    words.add(integerResult.unitWord)
    words.add(fractionalValue.toString())
    words.add(fractionalResult.unitWord)
    return words.joinToString(separator = " ")
}
```

В качестве параметров метод принимает на вход объект `AbstractUnitInfo` для целой части (рубли) и для дробной (копейки). Значения для этих валют содержатся в перечислении `CurrencyUnitInfo`.

Сначала мы проверяем, что текущее значение суммы находится в допустимых границах типа `Long`. Также мы должны быть уверены, что точность - два знака после запятой.

После этого мы извлекаем из суммы отдельно целую и дробную части. Для каждой такой части вызываем метод `convertNumberToWordsWithUnit()`. В конце объединяем все слова в единую текстовую строку с помощью пробелов.

Вызов данного метода будет выглядеть так:

```kotlin
BigDecimal("100.21").toTextWithUnits(CurrencyUnitInfo.USD, CurrencyUnitInfo.CENT)
// метод вернёт строку "сто долларов 21 цент"
```

Вы можете легко изменить логику формирования текстовой строки в зависимости от ваших потребностей.
