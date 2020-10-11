@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.PI
import kotlin.math.sqrt
import kotlin.math.acos

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    return when {
        age == 1 -> "1 год"
        age < 5 -> "$age года"
        age < 21 -> "$age лет"
        age % 10 == 0 -> "$age лет"
        (age % 10 == 1 && age < 100) ->"$age год"
        age % 10 == 1 -> "$age лет"
        age % 10 < 5 -> "$age года"
        else -> "$age лет"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    var halfWay : Double = (t1 * v1 + t2 * v2 + t3 * v3) / 2.0
    if (t1 * v1 <= halfWay) {
        halfWay -= t1 * v1
        if (t2 * v2 <= halfWay) {
            halfWay -= t2 * v2
            return t1 + t2 + halfWay / v3
        }
        else {
            return t1 + halfWay / v2
        }
    }
    else {
        return halfWay / v1
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    var result : Int = 0
    if (kingX == rookX1 || kingY == rookY1) {
        result++
    }
    if (kingX == rookX2 || kingY == rookY2) {
        result++
    }
    if (result == 2) {
        return 3
    }
    if (result == 1) {
        return when {
            kingX == rookX1 || kingY == rookY1 -> 1
            else -> 2
        }
    }
    return 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    var result : Int = 0
    if (kingX == rookX || kingY == rookY) {
        result++
    }
    if (kingX - bishopX != 0) {
        val k : Double = (kingY.toDouble() - bishopY.toDouble()) / (kingX.toDouble() - bishopX.toDouble())
        if (abs(k) == 1.0) {
            result++
        }
    }
    if (result == 0) {
        return 0
    }
    if (result == 2) {
        return 3
    }
    return when {
        kingX == rookX || kingY == rookY -> 1
        else -> 2
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    if (a + b <= c || a + c <= b || b + c <= a) {
        return -1
    }
    val value1 : Double = (sqr(b) + sqr(c) - sqr(a)) / (2 * b * c)
    val value2 : Double = (sqr(a) + sqr(c) - sqr(b)) / (2 * a * c)
    val value3 : Double = (sqr(b) + sqr(a) - sqr(c)) / (2 * b * a)
    return when {
        value1 == 0.0 || value2 == 0.0 || value3 == 0.0 -> 1
        acos(value1) < PI / 2.0 && acos(value2) < PI / 2.0 && acos(value3) < PI / 2.0 -> 0
        else -> 2
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if (a > d || c > b) {
        return -1
    }
    if (b == c || d == a) {
        return 0
    }
    if (c in a..b && d in a..b) {
        return d - c
    }
    if (a in c..d && b in c..d) {
        return b - a
    }
    return when {
        a > c && d > a -> d -a
        else -> b - c
    }
}
