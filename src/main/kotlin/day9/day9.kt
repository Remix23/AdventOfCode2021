package day9

import java.io.File

fun main ()
{
    val board = loadMap("C:\\Users\\keruj\\IdeaProjects\\KotlinAdventOfCode\\src\\main\\inputs\\day9.txt")

    val lowPoints = getLowPoints(board)

    var sumOfRiskLevels = 0
    lowPoints.forEach{
        sumOfRiskLevels += it + 1
    }
    println(sumOfRiskLevels)
}

fun loadMap (path : String) : Array<Array<Int>>
{
    val file = File(path)
    val board = mutableListOf<Array<Int>>()
    file.forEachLine {
        println(it)
        val row = it.split("").filter { it.length > 0 }.map { it.toInt() }.toTypedArray()
        board.add(row)
    }

    return board.toTypedArray()
}

fun getLowPoints (board: Array<Array<Int>>) : MutableList<Int>
{
    val lowPoints = mutableListOf<Int>()
    for (y in board.indices)
    {
        for (x in board[y].indices)
        {
            if (isLowPoint(x, y, board))
            {
                lowPoints.add(board[y][x])
            }
        }
    }
    return lowPoints
}

fun isLowPoint (x : Int, y : Int, board : Array<Array<Int>>) : Boolean
{
    val current = board[y][x]
    for (i in -1..1)
    {
        for (j in -1..1)
        {
            if (validateCords(x + j, y + i, board[0].size, board.size) && (i == 0 || j == 0) && !(i == 0 && j == 0))
            {
                if (board[y + i][x + j] <= current) {
                    return false
                }
            }
        }
    }
    return true
}

fun validateCords (x : Int, y : Int, maxX : Int, maxY : Int) : Boolean
{
    return !(x < 0 || x >= maxX || y < 0 || y >= maxY)
}