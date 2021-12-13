package day11

import java.io.File

fun main ()
{
    val board = loadMap("C:\\Users\\keruj\\IdeaProjects\\KotlinAdventOfCode\\src\\main\\inputs\\in11.txt")

    var result = 0
    var numOfSimSteps = 1

    while (true)
    {
        val flashes = mutableSetOf<Pair<Int, Int>>()
        for (y in board.indices)
        {
            for (x in board.indices)
            {
                if (updateCell(x, y, board))
                {
                    flashes.add(Pair(x, y))
                }
            }
        }
        if (processFlashes(flashes, board) == 100) break
        processStep(board)
        numOfSimSteps++
    }
    println(numOfSimSteps)
}

fun loadMap (path : String) : Array<Array<Triple<Int, Int, Boolean>>>
{
    val board = mutableListOf<Array<Triple<Int, Int, Boolean>>>()

    val f = File(path)
    f.forEachLine { it ->
        val row = it.split("").filter { it.isNotEmpty() }.map { Triple(it.toInt(), it.toInt(), false) }.toTypedArray() // zmienić na koordynaty
        board.add(row)
    }

    return board.toTypedArray()
}

fun getNeighbours (x : Int, y : Int, board : Array<Array<Triple<Int, Int, Boolean>>>) : MutableList<Pair<Int, Int>>
{
    val neighbours = mutableListOf<Pair<Int, Int>>()
    for (i in -1..1)
    {
        for (j in -1..1)
        {
            if (validateCords(x + j, y + i, board.first().size, board.size ) && !(i == 0 && j == 0))
            {
                neighbours.add(Pair(x + j, y + i))
            }
        }
    }
    return neighbours
}

fun updateCell (x : Int, y : Int, board: Array<Array<Triple<Int, Int, Boolean>>>) : Boolean// update the value of the cell by one || make it explode
{
    if (board[y][x].third) return false

    board[y][x] = board[y][x].copy(first = board[y][x].first + 1, second = board[y][x].first + 1)
    return board[y][x].first > 9
}

fun processFlashes (flashes: MutableSet<Pair<Int, Int>>, board: Array<Array<Triple<Int, Int, Boolean>>>) : Int
{
    var counter = 0
    while (flashes.isNotEmpty())
    {
        val current = flashes.first()
        flashes.remove(current)

        board[current.second][current.first] = Triple(first = 0, second = 0, third = true)
        counter++

        val neighbours = getNeighbours(current.first, current.second, board)

        neighbours.forEach {
            if (updateCell(it.first, it.second, board)) flashes.add(it)
        }
    }
    return counter
}

fun processStep (board: Array<Array<Triple<Int, Int, Boolean>>>)
{
    for (y in board.indices)
    {
        for (x in board[y].indices)
        {
            board[y][x] = board[y][x].copy(first = board[y][x].second, third = false)
        }
    }
}

fun validateCords (x : Int, y : Int, maxX : Int, maxY : Int) : Boolean
{
    return !(x < 0 || x >= maxX || y < 0 || y >= maxY)
}
