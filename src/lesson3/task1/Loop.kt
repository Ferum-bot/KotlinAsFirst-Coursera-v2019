@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.PI
import kotlin.math.sqrt
import kotlin.math.min

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var currentNumber : Int = n
    var result : Int = 0
    do {
        result++
        currentNumber /= 10
    } while (currentNumber != 0)
    return result
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n == 1) {
        return 1
    }
    if (n == 2) {
        return 1
    }
    return fib(n - 1) + fib(n - 2)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */

fun nod(m : Int, n : Int) : Int {
    if (m == 0) {
        return n
    }
    if (n == 0) {
        return m
    }
    return nod(n, m % n)
}

fun lcm(m: Int, n: Int): Int {
    return m * n / nod(m, n)
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..sqrt(n.toDouble()).toInt() + 1) {
        if (n % i == 0) {
            return i
        }
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in 2..sqrt(n.toDouble()).toInt() + 1) {
        if (n % i == 0) {
            return n / i
        }
    }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    if (m % n == 0 || n % m == 0) {
        return false
    }
    for (i in 2..sqrt(min(m, n).toDouble()).toInt() + 1) {
        if (m % i == 0 && n % i == 0) {
            return false
        }
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var currentK : Double = 0.0
    while (currentK * currentK <= n.toDouble()) {
        currentK++
        if (currentK * currentK >= m.toDouble() && currentK * currentK <= n.toDouble()) {
            return true
        }
    }
    return false
}

fun main() {
    println(squareSequenceDigit(1))
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    if (x == 1) {
        return 0
    }
    return when {
        x % 2 == 0 -> collatzSteps(x / 2) + 1
        else -> collatzSteps(3 * x + 1) + 1
    }
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    if (x == 0.0) {
        return 0.0;
    }
    var newx : Double = x
    while (newx >= 2 * PI) {
        newx -= 2 * PI
    }
    var currentPow : Double = 1.0
    var currentFactoril : Double = 1.0
    var currentValue : Double = newx;
    var currentSign : Double = 1.0;
    var result : Double = 0.0
    while (currentValue / currentFactoril >= eps) {
        result += currentSign * (currentValue / currentFactoril)
        currentSign *= -1.0
        currentValue *= newx * newx
        currentPow += 1.0
        currentFactoril *= currentPow
        currentPow += 1.0
        currentFactoril *= currentPow
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    if (x == 0.0) {
        return 1.0;
    }
    var newx : Double = x
    while (newx >= 2 * PI) {
        newx -= 2 * PI
    }
    var currentPow : Double = 0.0
    var currentFactoril : Double = 1.0
    var currentValue : Double = 1.0;
    var currentSign : Double = 1.0;
    var result : Double = 0.0
    while (currentValue / currentFactoril >= eps) {
        result += currentSign * (currentValue / currentFactoril)
        currentSign *= -1.0
        currentValue *= newx * newx
        currentPow += 1.0
        currentFactoril *= currentPow
        currentPow += 1.0
        currentFactoril *= currentPow
    }
    return result
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun countLen(n : Int) : Int {
    var currentN : Int = n
    var result = 0
    do {
        result++
        currentN /= 10
    } while (currentN != 0)
    return result
}

fun pow(n : Int, st : Int) : Int {
    var result : Int = 1
    for (i in 1..st) {
        result *= n
    }
    return result
}

fun revert(n: Int): Int {
    var len : Int = countLen(n)
    var currentPow = pow(10, len - 1)
    var result : Int = 0
    var currentN = n
    do {
        result += (currentN % 10) * currentPow
        currentPow /= 10
        currentN /= 10
    } while (currentN != 0)
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = TODO()

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val len : Int = countLen(n)
    if (len == 1) {
        return false
    }
    for (i in 0..9) {
        var cnt : Int = 0
        var currentN = n
        while (currentN != 0) {
            if (currentN % 10 == i) {
                cnt++
            }
            currentN /= 10
        }
        if (cnt == len) {
            return false
        }
    }
    return true
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun getDigit(n : Int, pos : Int) : Int {
    var currentPos = 0
    var currentN = n
    do {
        currentPos++
        if (currentPos == pos) {
            break
        }
        currentN /= 10
    } while (currentN != 0)
    return currentN % 10
}

fun squareSequenceDigit(n: Int): Int {
    var currentLen : Int = 0;
    var currentValue : Int = 0;
    while (currentLen < n) {
        currentValue++
        currentLen += countLen(currentValue * currentValue)
    }
    val pos : Int = currentLen - n + 1
    return getDigit(currentValue * currentValue, pos)
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int = TODO()
