package day9

import java.io.File

fun main ()
{
    val board = loadMap("C:\\Users\\keruj\\IdeaProjects\\KotlinAdventOfCode\\src\\main\\inputs\\in9.txt")

    val lowPoints = getLowPoints(board)

    val basinSizes = mutableListOf<Int>()
    lowPoints.forEach{
        basinSizes.add(getBasinFromLowPoint(it.first, it.second, board))
        println(basinSizes.last())
    }
    basinSizes.sort()
    println(basinSizes[lowPoints.size - 1] * basinSizes[lowPoints.size - 2] * basinSizes[lowPoints.size - 3])
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

fun getBasinFromLowPoint (x : Int, y : Int, board: Array<Array<Int>>) : Int
{
    val basin = mutableSetOf<Pair<Int, Int>>()
    val toCheck = mutableSetOf<Pair<Int, Int>>()
    toCheck.add(Pair(x, y))

    while (toCheck.size > 0)
    {
        val current = toCheck.first()

        basin.add(current)
        toCheck.remove(current)

        val neighbours = getNeighbours(current.first, current.second, board)

        for (n in neighbours)
        {
            if (validateCords(n.first, n.second, board[0].size, board.size))
            {
                if ( board[n.second][n.first] > board[current.second][current.first] && board[n.second][n.first] < 9)
                {
                    toCheck.add(n)
                }
            }
        }
    }
    return basin.size
}

fun getLowPoints (board: Array<Array<Int>>) : MutableList<Pair<Int, Int>>
{
    val lowPoints = mutableListOf<Pair<Int, Int>>()
    for (y in board.indices)
    {
        for (x in board[y].indices)
        {
            if (isLowPoint(x, y, board))
            {
                lowPoints.add(Pair(x, y))
            }
        }
    }
    return lowPoints
}

fun isLowPoint (x : Int, y : Int, board : Array<Array<Int>>) : Boolean
{
    val current = board[y][x]
    val neighbours = getNeighbours(x, y, board)
    neighbours.forEach {
        if (current >= board[it.second][it.first]) return false
    }
    return true
}

fun getNeighbours (x : Int, y : Int, board: Array<Array<Int>>) : MutableList<Pair<Int, Int>>
{
    val neighbors = mutableListOf<Pair<Int, Int>>()
    for (i in -1..1)
    {
        for (j in -1..1)
        {
            if (validateCords(x + j, y + i, board[0].size, board.size) && (i == 0 || j == 0) && !(i == 0 && j == 0))
            {
                neighbors.add(Pair(x + j, y + i))
            }
        }
    }
    return neighbors
}

fun validateCords (x : Int, y : Int, maxX : Int, maxY : Int) : Boolean
{
    return !(x < 0 || x >= maxX || y < 0 || y >= maxY)
}
