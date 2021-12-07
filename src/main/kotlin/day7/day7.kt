package day7

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main ()
{
    var maxValue = 0
    val stream = readLine()!!.split(",").map { it.toInt() }

    stream.forEach { num -> maxValue = max(maxValue, num)}

    var minCost = calcCost(stream, 0)
    for (pos in 1 until maxValue)
    {
        minCost = min(minCost, calcCost(stream, pos))
    }

    println(minCost)
}

fun calcCost (positions : List<Int>, pos : Int) : Int
{
    var cost = 0
    for (p in positions)
    {
        var distance = abs(p - pos)
        cost += distance * (distance + 1) / 2
    }
    return cost
}
