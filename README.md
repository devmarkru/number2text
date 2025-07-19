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

### Большие числа
Библиотека поддерживает значения до `10^33` (дециллион). Для их преобразования
используйте методы, принимающие `BigInteger`:

```kotlin
val big = BigInteger.TEN.pow(33)
println(converter.convertNumberToText(big))
// "один дециллион"
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
Начиная с версии 2.1.0 размерности содержат признак `asText`, определяющий, следует ли записывать число словами или оставить в виде цифр. Методы `convertDecimalNumberToTextWithUnits()` и `convertDecimalNumberToWordsWithUnits()` принимают число `BigDecimal`, единицы измерения для целой и дробной частей и соотношение дробных единиц. Например, для рублей и копеек соотношение равно `100`.

```kotlin
val converter = Number2TextConverterImpl()
println(
    converter.convertDecimalNumberToTextWithUnits(
        BigDecimal("100.21"),
        UnitInfo(Gender.MALE, "доллар", "доллара", "долларов", asText = false),
        CurrencyUnitInfo.CENT,
        100,
    )
)
// "100 долларов двадцать один цент"
```

Метод самостоятельно округлит исходное значение до нужной точности и преобразует обе части числа с учётом нужных размерностей.

```kotlin
println(
    converter.convertDecimalNumberToWordsWithUnits(
        BigDecimal("1.05"),
        CurrencyUnitInfo.EUR,
        CurrencyUnitInfo.CENT,
        100,
    )
)
// ["один", "евро", "5", "центов"]
```
