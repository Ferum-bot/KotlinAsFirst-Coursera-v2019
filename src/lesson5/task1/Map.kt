@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    var reversGrades: MutableMap<Int, MutableList<String>> = mutableMapOf()
    for ((key, value) in grades) {
        val currentList: List<String>? = reversGrades[value]
        if (currentList == null) {
            reversGrades[value] = mutableListOf<String>(key)
            continue
        }
        reversGrades[value]!!.add(key)
    }
    var result: MutableMap<Int, List<String>> = mutableMapOf()
    for ((key, value) in reversGrades) {
        result[key] = value.toList()
    }
    return result
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    for ((key, valueA) in a) {
        val valueB: String? = b[key]
        when {
            valueB == null -> return false
            valueB != valueA -> return false
        }
    }
    return true
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): Unit {
    for ((keyB, valueB) in b) {
        val valueA: String = a[keyB] ?: continue
        if (valueA != valueB) {
            continue
        }
        a.remove(keyB)
    }
}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    var result: MutableSet<String> = mutableSetOf()
    val current: MutableSet<String> = a.toMutableSet()
    for (el in b) {
        if (current.contains(el)) {
            result.add(el)
        }
    }
    return result.toList()
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val allStrings: MutableMap<String, MutableSet<String>> = mutableMapOf()
    for ((key, value) in mapA) {
        allStrings[key] = mutableSetOf(value)
    }
    for ((key, value) in mapB) {
        val current: MutableSet<String>? = allStrings[key]
        if (current == null) {
            allStrings[key] = mutableSetOf(value)
            continue
        }
        allStrings[key]!!.add(value)
    }
    val result: MutableMap<String, String> = mutableMapOf()
    for ((key, value) in allStrings) {
        result[key] = value.joinToString(separator = ", ")
    }
    return result.toMap()
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val map: MutableMap<String, MutableSet<Double>> = mutableMapOf()
    for ((key, value) in stockPrices) {
        val current: MutableSet<Double>? = map[key]
        if (current == null) {
            map[key] = mutableSetOf(value)
            continue
        }
        map[key]!!.add(value)
    }
    val result: MutableMap<String, Double> = mutableMapOf()
    for ((key, value) in map) {
        result[key] = value.sum() / value.size
    }
    return result.toMap()
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var result: String? = null
    var minCost: Double = Double.MAX_VALUE
    for ((key, value) in stuff) {
        if (value.first != kind) {
            continue
        }
        if (value.second < minCost) {
            minCost = value.second
            result = key
        }
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    val lowerWord = word.toLowerCase()
    val curSet: MutableSet<Char> = mutableSetOf()
    for (el in chars) {
        curSet.add(el.toLowerCase())
    }
    for (el in lowerWord) {
        if (curSet.contains(el)) {
            continue
        }
        return false
    }
    return true
}

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val valueOfKeys: MutableMap<String, Int> = mutableMapOf()
    for (el in list) {
        val value: Int? = valueOfKeys[el]
        if (value == null) {
            valueOfKeys[el] = 1
        }
        else {
            valueOfKeys[el] = value + 1
        }
    }
    val result: MutableMap<String, Int> = mutableMapOf()
    for ((el, vall) in valueOfKeys) {
        if (vall == 1) {
            continue
        }
        result[el] = vall
    }
    return result.toMap()
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean = TODO()

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */

fun dfs(start: Int, v: Int, used: MutableList<Boolean>, result: MutableList<MutableList<Int>>, grf: MutableList<MutableList<Int>>): Unit {
    used[v] = true
    for (u in grf[v]) {
        if (used[u]) {
            continue
        }
        result[start].add(u)
        dfs(start, u, used, result, grf)
    }
}

fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    var cnt: Int = 0
    val nameToId: MutableMap<String, Int> = mutableMapOf()
    val idToName: MutableMap<Int, String> = mutableMapOf()
    var used: MutableList<Boolean> = mutableListOf()
    val grf: MutableList<MutableList<Int>> = mutableListOf()
    val result: MutableList<MutableList<Int>> = mutableListOf()
    for ((key, value) in friends) {
        var currentName: Int? = nameToId[key]
        if (currentName == null) {
            nameToId[key] = cnt
            idToName[cnt] = key
            cnt++
        }
        for (el in value) {
            currentName = nameToId[el]
            if (currentName == null) {
                nameToId[el] = cnt
                idToName[cnt] = el
                cnt++
            }
        }
    }
    for (i in 0 until cnt) {
        grf.add(mutableListOf())
        used.add(false)
        result.add(mutableListOf())
    }
    for ((v, list) in friends) {
        for (u in list) {
            val vIndex: Int = nameToId[v]!!
            val uIndex: Int = nameToId[u]!!
            grf[vIndex].add(uIndex)
        }
    }
    for (i in 0 until cnt) {
        dfs(i, i, used, result, grf)
        used.fill(false)
    }
    val answer: MutableMap<String, Set<String>> = mutableMapOf()
    for (i in 0 until cnt) {
        val currentAnswer: MutableSet<String> = mutableSetOf()
        for (j in result[i]) {
            currentAnswer.add(idToName[j]!!)
        }
        answer[idToName[i]!!] = currentAnswer
    }
    return answer.toMap()
}



/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    var result: Pair<Int, Int> = Pair(-1, -1)
    var currentList: List<Pair<Int, Int>> = listOf()
    val n = list.size
    for (i in 0 until n) {
        currentList += Pair(list[i], i)
    }
    currentList = currentList.sortedBy { it.first }
    for (i in 0 until n - 1) {
        if (currentList[i].first + currentList[n - 1].first < number) {
            continue
        }
        var l: Int = i
        var r: Int = n - 1
        while (r - l > 1) {
            val mid: Int = (r + l) / 2
            if (currentList[i].first + currentList[mid].first >= number) {
                r = mid
            }
            else {
                l = mid
            }
        }
        if (currentList[i].first + currentList[r].first == number) {
            result = Pair(currentList[i].second, currentList[r].second)
        }
    }
    if (result.first > result.second) {
        result = Pair(result.second, result.first)
    }
    return result
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> = TODO()
