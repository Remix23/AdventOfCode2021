package day13

import java.io.File

fun main () {
    val (paper, commands) = loadPaper("C:\\Users\\keruj\\IdeaProjects\\KotlinAdventOfCode\\src\\main\\inputs\\in13.txt")

    for (cmd in commands)
    {
        fold(paper, cmd.second, cmd.first)
    }

    println(calcPoints(paper))
    for (row in paper)
    {
        for (elem in row)
        {
            if (elem) print('#')
            else print('.')
        }
        println()
    }
}

fun loadPaper (path : String) : Pair<MutableList<MutableList<Boolean>>, MutableList<Pair<Char, Int>>>
{
    val file = File(path)
    val nums = mutableListOf<Pair<Int, Int>>()
    val commands = mutableListOf<Pair<Char, Int>>()

    var maxX = 0
    var maxY = 0

    file.forEachLine {
        if (it.contains(',' ))
        {
            val num = it.split(",").filter { it.isNotEmpty() }.map { it.toInt() }
            if (num.first() > maxX) maxX = num.first()
            if (num.last() > maxY) maxY = num.last()
            nums.add(Pair(num.first(), num.last()))
        } else if (it.isNotEmpty()) {
            val cmd = it.split(" ").first { it.isNotEmpty() && it.contains("=") }
            val cmdArgs = cmd.split("=")
            commands.add(Pair(cmdArgs.first().first(), cmdArgs.last().toInt()))
        }
    }
    val board = MutableList(maxY + 1) { MutableList(maxX + 1) { false } }

    nums.forEach {
        board[it.second][it.first] = true
    }

    return Pair(board, commands)
}

fun fold (paper : MutableList<MutableList<Boolean>>, line : Int, orientation : Char)
{
    if (orientation == 'x')
    {
        for (y in 0 until paper.size) {
            for (x in 0 until line) {
                paper[y][x] = paper[y][x] || paper[y][paper[y].size - x - 1]
            }
            for (i in 0 until (paper[y].size - line))
            {
                paper[y].removeAt(paper[y].size - 1)
            }
        }
    } else
    {
        for (y in 0 until line)
        {
            for (x in 0 until paper.first().size)
            {
                paper[y][x] = paper[y][x] || paper[paper.size - y - 1][x]
            }
        }
        for (i in 0 until (paper.size - line))
        {
            paper.removeAt(paper.size - 1)
        }
    }
}

fun calcPoints (paper: MutableList<MutableList<Boolean>>) : Long
{
    var result = 0L
    for (row in paper)
    {
        for (elem in row)
        {
            if (elem) result++
        }
    }
    return result
}