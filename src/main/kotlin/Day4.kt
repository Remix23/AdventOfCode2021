fun main ()
{
    val stream = loadStream()
    val boards = loadBoards()

    for (i in stream.indices)
    {
        for (board in boards)
        {
            if (updateBoard(board, stream[i]))
            {
                println(calculateFinal(board, stream[i]))
                return
            }
        }
    }
}

fun checkBoard (board : Array<Array<Pair<Int, Boolean>>>, lastNumPosition : Pair<Int, Int>) : Boolean
{
    var result = true
    for (row in board)
    {
        if (!row[lastNumPosition.first].second)
        {
            result = false
        }
    }
    if (result) return true
    for (pair in board[lastNumPosition.second])
    {
        if (!pair.second)
        {
            return false
        }
    }
    return true
}

fun updateBoard (board : Array<Array<Pair<Int, Boolean>>>, numToUpdate : Int) : Boolean
{
    var temp = false
    for (y in board.indices)
    {
        for (x in board[y].indices)
        {
            if (board[y][x].first == numToUpdate) {
                board[y][x] = board[y][x].copy(second = true)
                if (checkBoard(board, Pair(x, y)))
                {
                    temp = true
                }
            }
        }
        if (temp) return true
    }
    return false
}

fun loadBoards () : List<Array<Array<Pair<Int, Boolean>>>>
{

    val boards = mutableListOf<Array<Array<Pair<Int, Boolean>>>>()
    for (j in 0 until 100)
    {
        readLine()
        val board = Array(5) { arrayOf<Pair<Int, Boolean>>() }
        for (i in 0 until 5) {
            val stringToParse = readLine()
            val numbers = stringToParse!!.split(" ").filter { it.isNotBlank() }.map { Pair(it.trim().toInt(), false) }.toTypedArray()
            board[i] = numbers
        }
        boards.add(board)
    }
    return boards
}

fun loadStream () : List<Int>
{
    val stringToParse = readLine()
    return stringToParse!!.split(",").map { it.toInt() }
}

fun calculateFinal (board: Array<Array<Pair<Int, Boolean>>>, winningNum : Int) : Int
{
    var sum = 0
    for (row in board)
    {
        for (pair in row)
        {
            if (!pair.second)
            {
                sum += pair.first
            }
        }
    }
    println("winning num: $winningNum")
    return sum * winningNum
}