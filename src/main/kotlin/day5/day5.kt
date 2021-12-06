package day5

import kotlin.math.max

fun main ()
{
    val numOfVectors = 500
    val (board, vectors) = prepareBoard(numOfVectors)

    for (vec in vectors)
    {
        updateBoard(board, vec)
    }

    println(calculateResult(board))
}

fun prepareBoard (numOfVectors: Int) : Pair<Array<Array<Int>>, MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>>>
{
    var rowCount = 0
    var columnCount = 0

    val vectors = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()

    for (i in 0 until numOfVectors)
    {
        val vec = readVec()
        rowCount = max(rowCount, max(vec.first.first, vec.second.first))
        columnCount = max(columnCount, max(vec.first.second, vec.second.second))
        vectors.add(vec)
    }
    val board = Array(columnCount + 1) { Array(rowCount + 1) {0} }

    return Pair(board, vectors)
}

fun updateBoard (board : Array<Array<Int>>, vec : Pair<Pair<Int, Int>, Pair<Int, Int>>)
{
    if (vec.first.first == vec.second.first) // vertical line
    {
        for (y in vec.first.second..vec.second.second)
        {
            board[y][vec.first.first]++

        }
    } else if (vec.first.second == vec.second.second) // horizontal lines
    {
        for (x in vec.first.first..vec.second.first)
        {
            board[vec.first.second][x]++
        }
    } else {

        // x2 > x1 and y1 < y2
        println(vec)
        if (vec.first.first > vec.second.first && vec.first.second < vec.second.second)
        {
            for (i in 0..(vec.first.first - vec.second.first))
            {
                board[vec.first.second + i][vec.first.first - i]++
            }
        }
        else if (vec.first.first < vec.second.first && vec.first.second < vec.second.second)
        {
            for (i in 0..(vec.second.first - vec.first.first))
            {
                board[vec.first.second + i][vec.first.first + i]++
            }
        }
        else {
            for (i in 0..(vec.second.first - vec.first.first))
            {
                board[vec.first.second - i][vec.first.first + i]++
            }
        }
    }
}

fun isHorizontalOrVertical (vec : Pair<Pair<Int, Int>, Pair<Int, Int>>) : Boolean
{
    return vec.first.first == vec.second.first || vec.first.second == vec.second.second
}

fun readVec () : Pair<Pair<Int, Int>, Pair<Int,Int>>
{
    val inputVec = readLine()!!.split(" -> " , ",").map { it.toInt() }

    var vec = Pair(Pair(inputVec[0], inputVec[1]), Pair(inputVec[2], inputVec[3]))

    if (vec.first.first > vec.second.first || vec.first.second > vec.second.second)
    {
        vec = Pair(vec.second, vec.first)
    }

    return vec
}

fun calculateResult (board: Array<Array<Int>>) : Int
{
    var result = 0

    for (row in board)
    {
        for (point in row)
        {
            if (point >= 2)
            {
                result++
            }
        }
    }

    return result
}

